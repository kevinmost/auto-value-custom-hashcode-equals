package com.kevinmost.auto.value.custom_hashcode_equals

import com.squareup.javapoet.AnnotationSpec
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
