package fr.grand_plateau.administration.domain.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NiveauCourseTest {

  @Test
  void should_return_correct_NiveauCourse_from_code() {
    Assertions.assertAll(() -> {
      assertEquals(NiveauCourse.WORLD_TOUR, NiveauCourse.fromCode("WT"));
      assertEquals(NiveauCourse.PRO_SERIES, NiveauCourse.fromCode("PS"));
      assertEquals(NiveauCourse.CLASSE_1, NiveauCourse.fromCode("I"));
      assertEquals(NiveauCourse.CLASSE_2, NiveauCourse.fromCode("II"));
      assertEquals(NiveauCourse.CHAMPIONNAT, NiveauCourse.fromCode("C"));
      assertEquals(NiveauCourse.JEUX_CHPT_INTERNAT, NiveauCourse.fromCode("JR"));
      assertThrows(IllegalArgumentException.class, () -> NiveauCourse.fromCode("TEST"));
    });
  }
}
