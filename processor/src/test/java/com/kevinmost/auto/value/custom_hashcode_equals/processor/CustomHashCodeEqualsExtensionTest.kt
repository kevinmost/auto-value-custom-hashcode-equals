package com.kevinmost.auto.value.custom_hashcode_equals.processor

import com.google.auto.value.processor.AutoValueProcessor
import com.google.common.truth.Truth
import com.google.testing.compile.JavaFileObjects
import com.google.testing.compile.JavaSourcesSubjectFactory
import org.junit.Test
import javax.tools.JavaFileObject

class CustomHashCodeEqualsExtensionTest {
  @Test fun `test hashCode generated as expected`() {
    Truth.assertAbout(JavaSourcesSubjectFactory.javaSources())
        .that(listOf(sourceFile("test/Test.java")))
        .processedWith(AutoValueProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(sourceFile("test/AutoValue_Test.java"))
  }
}

fun sourceFile(name: String): JavaFileObject {
  return JavaFileObjects.forResource(name)
}
