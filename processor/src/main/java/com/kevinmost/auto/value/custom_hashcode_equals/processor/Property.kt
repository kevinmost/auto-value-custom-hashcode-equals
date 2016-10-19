package com.kevinmost.auto.value.custom_hashcode_equals.processor

import com.google.auto.value.extension.AutoValueExtension
import com.kevinmost.auto.value.custom_hashcode_equals.adapter.IgnoreForHashCodeEquals
import javax.lang.model.element.ExecutableElement

internal fun AutoValueExtension.Context.determineProperties(): List<Property> {
  return properties().values.map {
    Property(it, IgnoreForHashCodeEquals::class in it.annotations)
  }
}

internal data class Property(
    val element: ExecutableElement,
    val isIgnored: Boolean
)