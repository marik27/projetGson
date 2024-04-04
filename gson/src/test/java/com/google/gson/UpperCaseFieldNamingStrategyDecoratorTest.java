package com.google.gson;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import org.junit.Test;

public class UpperCaseFieldNamingStrategyDecoratorTest {

  @Test
  public void testTranslateName() {
    // Création d'une instance de FieldNamingStrategy à décorer
    FieldNamingStrategy originalStrategy =
        new FieldNamingStrategy() {
          @Override
          public String translateName(Field f) {
            // Simulation de différents types de noms de champs
            return "fieldName"; // Retourne toujours "fieldName"
          }
        };

    // Création d'une instance de UpperCaseFieldNamingStrategyDecorator en utilisant la stratégie
    // originale
    FieldNamingStrategy decorator = new UpperCaseFieldNamingStrategyDecorator(originalStrategy);

    // Appel de la méthode translateName() sur le décorateur pour différents types de noms de champs
    assertEquals(
        "FIELDNAME",
        decorator.translateName(null)); // Peu importe le champ, retourne toujours "FIELDNAME"
  }
}
