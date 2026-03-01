package fr.grand_plateau.administration.domain.model;

import fr.grand_plateau.administration.domain.enums.NiveauCourse;
import fr.grand_plateau.administration.domain.enums.PrestigeCourse;
import fr.grand_plateau.administration.domain.enums.ZoneChampionnat;

import java.util.Objects;
import java.util.UUID;

public record TypeCourse(
    UUID typeCourseId,
    NiveauCourse niveauCourse,
    ZoneChampionnat zoneChampionnat,
    Integer duree,
    PrestigeCourse prestigeCourse
) {

  public TypeCourse {
    Objects.requireNonNull(typeCourseId);
    Objects.requireNonNull(niveauCourse);
  }

  public static TypeCourse create(NiveauCourse niveauCourse,
                                  ZoneChampionnat zoneChampionnat,
                                  Integer duree,
                                  PrestigeCourse prestigeCourse) {
    validateArguments(niveauCourse, zoneChampionnat, duree, prestigeCourse);

    return new TypeCourse(UUID.randomUUID(), niveauCourse, zoneChampionnat, duree, prestigeCourse);
  }

  public String getBadgeCode() {
    return switch (niveauCourse) {
      case WORLD_TOUR, PRO_SERIES ->
          niveauCourse.getCode() + "-" + duree + (prestigeCourse != null ? prestigeCourse.toString() : "");
      case CLASSE_1, CLASSE_2 ->
          niveauCourse.getCode() + "." + duree;
      case CHAMPIONNAT ->
          zoneChampionnat().getCode() + (prestigeCourse != null ?  ("-" + prestigeCourse) : "");
      case JEUX_CHPT_INTERNAT ->
          niveauCourse.getCode();
    };
  }

  private static void validateArguments(NiveauCourse niveauCourse,
                                        ZoneChampionnat zoneChampionnat,
                                        Integer duree,
                                        PrestigeCourse prestigeCourse) {
    validatePrestigeArgument(niveauCourse, zoneChampionnat, prestigeCourse);
    validateZoneArgument(niveauCourse, zoneChampionnat);
    validateDureeArgument(niveauCourse, duree);
  }

  private static void validatePrestigeArgument(NiveauCourse niveauCourse,
                                           ZoneChampionnat zoneChampionnat,
                                           PrestigeCourse prestigeCourse) {
    // CAS 1 : World Tour ou Championnat National nécessitant un prestige, SANS prestige
    if ((isWorldTour(niveauCourse)
          || isChampionnatNational(niveauCourse, zoneChampionnat))
        && prestigeCourse == null) {
      throw new IllegalArgumentException("Le niveau de course de type " + niveauCourse.getLibelle()
          + " doit posséder un prestige");
    }

    // CAS 2 : Niveau autre que World Tour ou Championnant National ne nécessitant pas de prestige, AVEC prestige
    if (prestigeCourse != null
        && !isWorldTour(niveauCourse)
        && !isChampionnatNational(niveauCourse, zoneChampionnat)) {
        throw new IllegalArgumentException("Le niveau de course de type " + niveauCourse.getLibelle()
            + " ne doit pas posséder de prestige");
      }

  }

  private static void validateZoneArgument(NiveauCourse niveauCourse, ZoneChampionnat zoneChampionnat) {
    // CAS 1 : Championnat nécessitant une zone, SANS zone
    if (isChampionnat(niveauCourse) && zoneChampionnat == null) {
      throw new IllegalArgumentException("Les niveaux de course de type CHAMPIONNAT doivent posséder une zone");
    }

    // CAS 2 : Autre que Championnat ne nécessitant pas de zone, AVEC zone
    if (!isChampionnat(niveauCourse) && zoneChampionnat != null) {
      throw new IllegalArgumentException(
          "Les niveaux de course de type autre que CHAMPIONNAT ne doivent pas posséder de zone");
    }
  }

  private static void validateDureeArgument(NiveauCourse niveauCourse, Integer duree) {
    // CAS 1 : Niveau nécessitant une durée, SANS durée
    if ((isWorldTour(niveauCourse)
          || isProSeries(niveauCourse)
          || isClasse1(niveauCourse)
          || isClasse2(niveauCourse))
        && duree == null) {
      throw new IllegalArgumentException("Le niveau de type " + niveauCourse.getLibelle() + " doit posséder une durée");
    }

    // CAS 2 : Niveau ne nécessitant pas de durée, AVEC durée
    if ((isChampionnat(niveauCourse)
          || isJeuxOuChptsInternationaux(niveauCourse))
        && duree != null) {
      throw new IllegalArgumentException(
          "Le niveau de type " + niveauCourse.getLibelle() + " ne doit pas posséder de durée");
    }

    // CAS 3 : Durée négative ou supérieure à 2
    if (duree != null && (duree < 1 || duree > 2)) {
      throw new IllegalArgumentException(
          "La durée doit appartenir à ces valeurs : null, 1 ou 2. Duree renseignée : " + duree);
    }
  }

  private static boolean isWorldTour(NiveauCourse niveauCourse) {
    return NiveauCourse.WORLD_TOUR == niveauCourse;
  }

  private static boolean isProSeries(NiveauCourse niveauCourse) {
    return NiveauCourse.PRO_SERIES == niveauCourse;
  }

  private static boolean isClasse1(NiveauCourse niveauCourse) {
    return NiveauCourse.CLASSE_1 == niveauCourse;
  }

  private static boolean isClasse2(NiveauCourse niveauCourse) {
    return NiveauCourse.CLASSE_2 == niveauCourse;
  }

  private static boolean isChampionnat(NiveauCourse niveauCourse) {
    return NiveauCourse.CHAMPIONNAT == niveauCourse;
  }

  private static boolean isJeuxOuChptsInternationaux(NiveauCourse niveauCourse) {
    return NiveauCourse.JEUX_CHPT_INTERNAT == niveauCourse;
  }

  private static boolean isChampionnatNational(NiveauCourse niveauCourse, ZoneChampionnat zoneChampionnat) {
    return isChampionnat(niveauCourse) && ZoneChampionnat.NATIONAL == zoneChampionnat;
  }
}
