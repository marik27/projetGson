/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gson.metrics;

import com.google.caliper.BeforeExperiment;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;

public class BagOfPrimitivesDeserializationBenchmark extends AbstractDeserializationBenchmark {

  private String json;

  @BeforeExperiment
  void setUp() throws Exception {
    gson = new Gson();
    BagOfPrimitives bag = new BagOfPrimitives(10L, 1, false, "foo");
    json = gson.toJson(bag);
  }

  /** Benchmark to measure Gson performance for deserializing an object */
  public void timeBagOfPrimitivesDefault(int reps) {
    for (int i = 0; i < reps; ++i) {
      gson.fromJson(json, BagOfPrimitives.class);
    }
  }

  /** Benchmark to measure deserializing objects by hand */
  public void timeBagOfPrimitivesStreaming(int reps) throws IOException {
    for (int i = 0; i < reps; ++i) {
      StringReader reader = new StringReader(json);
      JsonReader jr = new JsonReader(reader);
      jr.beginObject();
      BagOfPrimitives bag = new BagOfPrimitives();
      while (jr.hasNext()) {
        String name = jr.nextName();
        setFieldByName(bag, name, jr);
      }
      jr.endObject();
    }
  }
}
