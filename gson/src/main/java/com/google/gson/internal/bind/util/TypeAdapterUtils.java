package com.google.gson.internal.bind.util;

import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class TypeAdapterUtils {
  private TypeAdapterUtils() {
    throw new AssertionError("TypeAdapterUtils should not be instantiated.");
  }

  public static void writeNumber(JsonWriter out, Number value, boolean asShort) throws IOException {
    if (value == null) {
      out.nullValue();
    } else {
      if (asShort) {
        out.value(value.shortValue());
      } else {
        out.value(value.longValue());
      }
    }
  }
}
