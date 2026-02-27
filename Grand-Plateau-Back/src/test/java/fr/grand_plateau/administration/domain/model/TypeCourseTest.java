package fr.grand_plateau.administration.domain.model;

import fr.grand_plateau.administration.domain.enums.NiveauCourse;
import fr.grand_plateau.administration.domain.enums.PrestigeCourse;
import fr.grand_plateau.administration.domain.enums.ZoneChampionnat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TypeCourseTest {
  @Test
  void should_create_TypeCourse_correctly() {
    // GIVEN
    TypeCourse expectedTypeCourse =
        new TypeCourse(UUID.randomUUID(), NiveauCourse.WORLD_TOUR,
            null, 1, PrestigeCourse.A);
    String expectedBadge = "WT-1A";

    // WHEN
    TypeCourse result = TypeCourse.create(NiveauCourse.WORLD_TOUR, null, 1, PrestigeCourse.A);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(expectedTypeCourse.niveauCourse(), result.niveauCourse());
      assertNull(result.zoneChampionnat());
      assertEquals(expectedTypeCourse.duree(), result.duree());
      assertEquals(expectedTypeCourse.prestigeCourse(), result.prestigeCourse());

      String resultBadge = result.getBadgeCode();
      assertFalse(resultBadge.isBlank());
      assertEquals(expectedBadge, resultBadge);
    });
  }

  @Test
  void should_throw_IllegalArgumentException_when_creating_malformed_TypeCourse() {
    // WT SANS prestige
    Assertions.assertThrows(IllegalArgumentException.class, () ->
        TypeCourse.create(NiveauCourse.WORLD_TOUR, null, 1, null));
    // Championnat Nat SANS prestige
    Assertions.assertThrows(IllegalArgumentException.class, () ->
        TypeCourse.create(NiveauCourse.CHAMPIONNAT, ZoneChampionnat.NATIONAL, null, null));
    // Championnat SANS zone
    Assertions.assertThrows(IllegalArgumentException.class, () ->
        TypeCourse.create(NiveauCourse.CHAMPIONNAT, null, null, null));
    // Niveau sans zone AVEC zone
    Assertions.assertThrows(IllegalArgumentException.class, () ->
        TypeCourse.create(NiveauCourse.CLASSE_1, ZoneChampionnat.JEUX_OLYMPIQUES, 1, null));
    // Niveau avec durée SANS durée
    Assertions.assertThrows(IllegalArgumentException.class, () ->
        TypeCourse.create(NiveauCourse.CLASSE_2, null, null, null));
    // Niveau sans durée AVEC durée
    Assertions.assertThrows(IllegalArgumentException.class, () ->
        TypeCourse.create(NiveauCourse.CHAMPIONNAT, ZoneChampionnat.MONDIAL, 1, null));
    // Niveau avec durée négative
    Assertions.assertThrows(IllegalArgumentException.class, () ->
        TypeCourse.create(NiveauCourse.PRO_SERIES, null, -1, null));
    // Niveau sans prestige AVEC prestige
    Assertions.assertThrows(IllegalArgumentException.class, () ->
        TypeCourse.create(NiveauCourse.PRO_SERIES, null, 1, PrestigeCourse.C));
  }

  @Test
  void should_return_correct_badge() {
    // GIVEN
    String wtBadge = "WT-2B";
    String psBadge = "PS-1";
    String c1Badge = "I.1";
    String c2Badge = "II.2";
    String natChptBadge = "NC-C";
    String joBadge = "JO";
    String jeuxChptIntBadge = "JR";

    // WHEN
    String wtResult =
        generateTypeCourse(NiveauCourse.WORLD_TOUR, null, 2, PrestigeCourse.B)
            .getBadgeCode();
    String psResult =
        generateTypeCourse(NiveauCourse.PRO_SERIES, null, 1, null)
            .getBadgeCode();
    String c1Result =
        generateTypeCourse(NiveauCourse.CLASSE_1, null, 1, null)
            .getBadgeCode();
    String c2Result =
        generateTypeCourse(NiveauCourse.CLASSE_2, null, 2, null)
            .getBadgeCode();
    String natChptResult =
        generateTypeCourse(NiveauCourse.CHAMPIONNAT, ZoneChampionnat.NATIONAL, null, PrestigeCourse.C)
            .getBadgeCode();
    String joResult =
        generateTypeCourse(NiveauCourse.CHAMPIONNAT, ZoneChampionnat.JEUX_OLYMPIQUES, null, null)
            .getBadgeCode();
    String jeuxChptIntResult =
        generateTypeCourse(NiveauCourse.JEUX_CHPT_INTERNAT, null, null, null)
            .getBadgeCode();

    // THEN
    Assertions.assertAll(() -> {
      assertEquals(wtBadge, wtResult);
      assertEquals(psBadge, psResult);
      assertEquals(c1Badge, c1Result);
      assertEquals(c2Badge, c2Result);
      assertEquals(natChptBadge, natChptResult);
      assertEquals(joBadge, joResult);
      assertEquals(jeuxChptIntBadge, jeuxChptIntResult);
    });
  }

  private static TypeCourse generateTypeCourse(NiveauCourse niveauCourse,
                                                     ZoneChampionnat zoneChampionnat, Integer duree,
                                                     PrestigeCourse prestigeCourse) {
    return new TypeCourse(UUID.randomUUID(),  niveauCourse, zoneChampionnat, duree, prestigeCourse);
  }
}
