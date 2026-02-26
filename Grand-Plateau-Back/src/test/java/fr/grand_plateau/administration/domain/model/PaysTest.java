package fr.grand_plateau.administration.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PaysTest {

  @Test
  void should_create_Pays_successfully() {
    // GIVEN
    String name = "Test";
    String isoCode = "TS";

    // WHEN
    Pays createdPays = Pays.create(name, isoCode);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(createdPays);
      assertEquals("TEST", createdPays.nom());
      assertEquals("TS", createdPays.codeIso());
    });
  }

  @Test
  void should_throw_IllegalArgumentException_when_nom_or_codeIso_malformed() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> Pays.create( "", "TS"));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Pays.create( null, "TS"));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Pays.create( "TEST", "Test"));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Pays.create( "TEST", ""));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Pays.create( "TEST", null));
  }
}
