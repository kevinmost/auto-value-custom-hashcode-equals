package com.kevinmost.auto.value.custom_hashcode_equals

import com.squareup.javapoet.AnnotationSpec
import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterSpec
import javax.lang.model.element.Modifier
import kotlin.reflect.KClass

fun annotationSpec(type: KClass<out Annotation>): AnnotationSpec {
  return AnnotationSpec.builder(type.java).build()
}

fun buildParam(
    name: String,
    type: KClass<*>,
    modifiers: List<Modifier> = emptyList(),
    annotations: List<AnnotationSpec> = emptyList()
): ParameterSpec.Builder {
  return ParameterSpec.builder(type.java, name, *modifiers.toTypedArray()).addAnnotations(annotations)
}

fun buildMethod(
    annotations: List<AnnotationSpec> = emptyList(),
    modifiers: List<Modifier> = emptyList(),
    name: String,
    returns: KClass<*>,
    params: List<ParameterSpec> = emptyList()
): MethodSpec.Builder {
  return MethodSpec.methodBuilder(name)
      .addAnnotations(annotations)
      .addModifiers(modifiers)
      .returns(returns.java)
      .addParameters(params)
}

inline fun MethodSpec.Builder.controlFlow(
    statement: String,
    args: List<String> = emptyList(),
    block: MethodSpec.Builder.() -> Unit
): MethodSpec.Builder {
  beginControlFlow(statement, *args.toTypedArray())
  block(this)
  endControlFlow()
  return this
}

inline internal fun MethodSpec.Builder.addMultiline(
    firstLine: String,
    lastLine: String,
    block: MultilineCodeBuilder.() -> Unit
) {
  addCode(MultilineCodeBuilder(firstLine, lastLine).apply { block(this) }.build())
}

internal class MultilineCodeBuilder(private val firstLine: String, private val lastLine: String) {

  private val lines = mutableListOf<Pair<String, Array<out String>>>()

  fun addIndentedStatement(code: String, vararg args: String) {
    lines += code to args
  }

  internal fun build(): CodeBlock {
    return CodeBlock.builder()
        .add(firstLine)
        .add("\n")
        .add("\$>\$>")
        .add("\$[")
        .apply {
          lines.forEach { line ->
            add(line.first, *line.second)
            add("\n")
          }
        }
        .add("\$]")
        .add("\$<\$<")
        .add(lastLine)
        .add("\n")
        .build()
  }

}

