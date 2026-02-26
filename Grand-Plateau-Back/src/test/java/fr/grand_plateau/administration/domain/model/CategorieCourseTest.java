package fr.grand_plateau.administration.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategorieCourseTest {
  @Test
  void should_create_CategorieCourse_successfully() {
    // GIVEN
    String nom = "Catégorie 1";
    String expectedAbreviation = "C1";

    // WHEN
    CategorieCourse createdCategorieCourse = CategorieCourse.create(nom);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(createdCategorieCourse);
      assertEquals("Catégorie 1", createdCategorieCourse.nom());
      assertEquals(expectedAbreviation, createdCategorieCourse.abreviation());
    });
  }

  @Test
  void should_throw_IllegalArgumentException_when_nom_malformed() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> CategorieCourse.create(""));
    Assertions.assertThrows(IllegalArgumentException.class, () -> CategorieCourse.create(null));
  }
}
