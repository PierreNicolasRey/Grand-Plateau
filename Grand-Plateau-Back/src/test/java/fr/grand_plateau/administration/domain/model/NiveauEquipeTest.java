package fr.grand_plateau.administration.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NiveauEquipeTest {
  @Test
  void should_create_NiveauEquipe_successfully() {
    // WHEN
    NiveauEquipe result = NiveauEquipe.create("world tour", "wt");

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals("WORLD TOUR", result.nom());
      assertEquals("WT", result.abreviation());
    });
  }

  @Test
  void should_throw_IllegalArgumentException_when_nom_and_abreviation_malformed() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> NiveauEquipe.create("", "WT"));
    Assertions.assertThrows(IllegalArgumentException.class, () -> NiveauEquipe.create(null, "WT"));
    Assertions.assertThrows(IllegalArgumentException.class, () -> NiveauEquipe.create("World Tour", ""));
    Assertions.assertThrows(IllegalArgumentException.class, () -> NiveauEquipe.create("World Tour", null));
  }
}
