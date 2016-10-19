package com.kevinmost.auto.value.custom_hashcode_equals.processor

import javax.lang.model.element.Element
import kotlin.reflect.KClass

val Element.annotations: Set<KClass<out Annotation>>
  get() = annotationMirrors.asSequence()
      .map { mirror -> mirror.annotationType.toString() }
      .map { qualifiedName -> Class.forName(qualifiedName).kotlin }
      .filterIsInstance<KClass<out Annotation>>()
      .toSet()
