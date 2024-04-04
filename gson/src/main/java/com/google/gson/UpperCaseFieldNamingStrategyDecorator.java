package com.google.gson;

import java.lang.reflect.Field;
import java.util.Locale;

/**
 * A decorator class that converts field names to uppercase using the provided FieldNamingStrategy.
 */
public class UpperCaseFieldNamingStrategyDecorator implements FieldNamingStrategy {

  private final FieldNamingStrategy delegate;

  /**
   * Constructs a new UpperCaseFieldNamingStrategyDecorator with the provided FieldNamingStrategy.
   *
   * @param delegate The FieldNamingStrategy to delegate to.
   */
  public UpperCaseFieldNamingStrategyDecorator(FieldNamingStrategy delegate) {
    this.delegate = delegate;
  }

  /**
   * Translates the field name to uppercase using the delegate FieldNamingStrategy.
   *
   * @param f The field for which the name should be translated.
   * @return The uppercase field name.
   */
  @Override
  public String translateName(Field f) {
    String translatedName = delegate.translateName(f);
    return translatedName.toUpperCase(Locale.ROOT);
  }
}
