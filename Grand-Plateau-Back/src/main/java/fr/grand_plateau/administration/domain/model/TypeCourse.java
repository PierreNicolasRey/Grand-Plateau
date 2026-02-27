package fr.grand_plateau.administration.domain.model;

import fr.grand_plateau.administration.domain.enums.NiveauCourse;
import fr.grand_plateau.administration.domain.enums.PrestigeCourse;
import fr.grand_plateau.administration.domain.enums.ZoneChampionnat;

import java.util.UUID;

public record TypeCourse(
    UUID id,
    NiveauCourse niveauCourse,
    ZoneChampionnat zoneChampionnat,
    Integer duree,
    PrestigeCourse prestigeCourse
) {

  public TypeCourse {

  }

  public static TypeCourse create(NiveauCourse niveauCourse,
                                  ZoneChampionnat zoneChampionnat,
                                  Integer duree,
                                  PrestigeCourse prestigeCourse) {
    return null;
  }

  public String getBadgeCode() {
    return "";
  }
}
