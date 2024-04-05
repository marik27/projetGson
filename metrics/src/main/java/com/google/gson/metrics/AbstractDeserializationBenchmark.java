package com.google.gson.metrics;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.lang.reflect.Field;

abstract class AbstractDeserializationBenchmark {
  protected Gson gson;

  protected void setFieldByName(BagOfPrimitives bag, String name, JsonReader jr)
      throws IOException {
    try {
      Field field = BagOfPrimitives.class.getDeclaredField(name);
      Class<?> fieldType = field.getType();
      if (fieldType.equals(long.class)) {
        field.setLong(bag, jr.nextLong());
      } else if (fieldType.equals(int.class)) {
        field.setInt(bag, jr.nextInt());
      } else if (fieldType.equals(boolean.class)) {
        field.setBoolean(bag, jr.nextBoolean());
      } else if (fieldType.equals(String.class)) {
        field.set(bag, jr.nextString());
      } else {
        throw new RuntimeException("Unexpected: type: " + fieldType + ", name: " + name);
      }
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new IOException("Error setting field: " + e.getMessage());
    }
  }
}
