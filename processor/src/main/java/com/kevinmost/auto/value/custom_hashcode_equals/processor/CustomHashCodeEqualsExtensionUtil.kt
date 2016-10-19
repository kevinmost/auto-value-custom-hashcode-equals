@file:JvmName("CustomHashCodeEqualsExtensionUtil")

package com.kevinmost.auto.value.custom_hashcode_equals.processor

import com.google.auto.value.extension.AutoValueExtension
import com.kevinmost.auto.value.custom_hashcode_equals.adapter.IgnoreForHashCodeEquals
import com.kevinmost.auto.value.custom_hashcode_equals.annotationSpec
import com.kevinmost.auto.value.custom_hashcode_equals.buildMethod
import com.kevinmost.auto.value.custom_hashcode_equals.buildParam
import com.kevinmost.auto.value.custom_hashcode_equals.controlFlow
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.Modifier
import javax.lang.model.element.Name
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror

internal fun CustomHashCodeEqualsExtensionShim.isApplicable(context: AutoValueExtension.Context): Boolean {
  return context.properties().values.any { property -> IgnoreForHashCodeEquals::class in property.annotations }
}

internal fun CustomHashCodeEqualsExtensionShim.generateClass(
    context: AutoValueExtension.Context,
    className: String,
    classToExtend: String,
    isFinal: Boolean
): String {
  val packageName = context.packageName()
  val hashCodeAndEqualsProperties = context.determineProperties()
  return JavaFile.builder(packageName, TypeSpec.classBuilder(className)
      .addModifiers(if (isFinal) Modifier.FINAL else Modifier.ABSTRACT)
      .superclass(ClassName.get(packageName, classToExtend))
      .addMethod(generateConstructor(context.properties()))
      .addMethod(generateEquals(context.autoValueClass().simpleName.toString(), hashCodeAndEqualsProperties))
      .addMethod(generateHashCode(hashCodeAndEqualsProperties))
      .build()
  )
      .build().toString()

}

private fun generateHashCode(properties: List<Property>): MethodSpec {
  return buildMethod(
      name = "hashCode",
      annotations = listOf(annotationSpec(Override::class)),
      modifiers = listOf(Modifier.PUBLIC, Modifier.FINAL),
      returns = Int::class
  )
      .addStatement("int h = 1")
      .apply {
        properties.forEach {
          val (property, isIgnored) = it
          val propertyName = property.simpleName
          val propertyType = property.returnType
          if (isIgnored) {
            addCode("// ${skippedMessage(propertyName, propertyType)}\n")
            return@forEach
          }
          val generated = when (propertyType.kind) {
            TypeKind.ARRAY -> "java.util.Arrays.hashCode(this.$propertyName())"
            TypeKind.BOOLEAN -> "this.$propertyName() ? 1231 : 1237"
            TypeKind.BYTE, TypeKind.CHAR, TypeKind.SHORT, TypeKind.INT -> "this.$propertyName()"
            TypeKind.LONG -> "(this.$propertyName() >>> 32) ^ this.$propertyName()"
            TypeKind.FLOAT -> "(Float.floatToIntBits(this.$propertyName()))"
            TypeKind.DOUBLE -> "(Double.doubleToLongBits(this.$propertyName()) >>> 32) ^ Double.doubleToLongBits(this.$propertyName())"
            else -> "this.$propertyName() == null ? 0 : this.$propertyName().hashCode()"
          }
          addStatement("h *= 1000003")
          addStatement("h ^= $generated")
        }
      }
      .addStatement("return h")
      .build()
}

private fun generateEquals(superName: String, properties: List<Property>): MethodSpec {
  return buildMethod(
      name = "equals",
      annotations = listOf(annotationSpec(Override::class)),
      modifiers = listOf(Modifier.PUBLIC, Modifier.FINAL),
      returns = Boolean::class,
      params = listOf(buildParam(name = "o", type = Any::class).build())
  )
      .controlFlow("if (o == this)") {
        addStatement("return true")
      }
      .controlFlow("if (o instanceof $superName)") {
        addStatement("$superName that = ($superName) o")

        addCode("return\n")
        addCode("\$>\$>")
        var atLeastOneClauseHit = false
        properties.forEach {
          val (element, isIgnored) = it
          val propertyName = element.simpleName
          val propertyType = element.returnType
          val kind = propertyType.kind

          if (atLeastOneClauseHit && !isIgnored) addCode("&& ")
          addCode(if (isIgnored) {
            "// ${skippedMessage(propertyName, element.returnType)}"
          } else if (kind == TypeKind.ARRAY) {
            "java.util.Arrays.equals(this.$propertyName(), that.$propertyName())"
          } else if (kind.isPrimitive) {
            "this.$propertyName() == that.$propertyName()"
          } else {
            "(this.$propertyName() == null) ? (that.$propertyName() == null) : this.$propertyName().equals(that.$propertyName())"
          })
          addCode("\n")
          atLeastOneClauseHit = atLeastOneClauseHit || !isIgnored // if we've hit at least one clause already, we need to generate a "&&"
        }
        addCode("\$<\$<")
        addCode(";")
        addCode("\n")
      }
      .addStatement("return false")
      .build()
}

private fun generateConstructor(properties: Map<String, ExecutableElement>): MethodSpec {
  val params = properties.map {
    val (propertyName, property) = it
    val typeName = TypeName.get(property.returnType)
    ParameterSpec.builder(typeName, propertyName).apply {
      property.annotations
          .asSequence()
          .filter { annotationClass -> annotationClass.simpleName in arrayOf("NotNull", "NonNull", "Nullable") }
          .map(::annotationSpec)
          .asIterable()
          .let { annotationSpecs -> addAnnotations(annotationSpecs) }
    }.build()
  }
  return MethodSpec.constructorBuilder()
      .addParameters(params)
      .addStatement(properties.map { it.key }.joinToString(prefix = "super(", postfix = ")"))
      .build()
}

private fun skippedMessage(propertyName: Name, propertyType: TypeMirror) = "$propertyName (type: $propertyType) skipped due to @${IgnoreForHashCodeEquals::class.java.simpleName} annotation"