package fr.grand_plateau.administration.domain.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ZoneChampionnatTest {

  @Test
  void should_return_correct_ZoneChampionnat_from_code() {
    Assertions.assertAll(() -> {
      assertEquals(ZoneChampionnat.CONTINENTAL, ZoneChampionnat.fromCode("CC"));
      assertEquals(ZoneChampionnat.JEUX_OLYMPIQUES, ZoneChampionnat.fromCode("JO"));
      assertEquals(ZoneChampionnat.MONDIAL, ZoneChampionnat.fromCode("WC"));
      assertEquals(ZoneChampionnat.NATIONAL, ZoneChampionnat.fromCode("NC"));
      assertThrows(IllegalArgumentException.class, () -> ZoneChampionnat.fromCode("TEST"));
    });
  }
}
