package com.kevinmost.auto.value.custom_hashcode_equals.adapter;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * When using this annotation, the hashCode/equals for the target AutoValue type will be calculated as normal, but any
 * properties annotated with this annotation will not be used (and thus will not contribute to the object's identity)
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD})
public @interface IgnoreForHashCodeEquals {}
