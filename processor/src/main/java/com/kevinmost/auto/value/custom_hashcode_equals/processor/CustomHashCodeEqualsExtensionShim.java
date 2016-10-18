package com.kevinmost.auto.value.custom_hashcode_equals.processor;

import com.google.auto.service.AutoService;
import com.google.auto.value.extension.AutoValueExtension;

@AutoService(AutoValueExtension.class)
public final class CustomHashCodeEqualsExtensionShim extends AutoValueExtension {

  @Override public boolean applicable(Context context) {
    return CustomHashCodeEqualsExtensionUtil.isApplicable(this, context);
  }

  public String generateClass(Context context,
      String className,
      String classToExtend,
      boolean isFinal) {
    return CustomHashCodeEqualsExtensionUtil.generateClass(this, context, className, classToExtend, isFinal);
  }
}
