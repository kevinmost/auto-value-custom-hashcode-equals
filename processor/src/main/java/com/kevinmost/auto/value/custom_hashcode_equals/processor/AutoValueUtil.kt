package com.kevinmost.auto.value.custom_hashcode_equals.processor

import javax.lang.model.element.Element

val Element.annotationSimpleNames: Set<String>
  get() = annotationMirrors.asSequence().map { it.annotationType.asElement().simpleName.toString() }.toSet()
