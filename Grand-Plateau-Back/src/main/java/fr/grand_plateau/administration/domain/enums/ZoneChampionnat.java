package fr.grand_plateau.administration.domain.enums;

public enum ZoneChampionnat {
  CONTINENTAL("Continental", "CC"),
  JEUX_OLYMPIQUES("Jeux Olympiques", "JO"),
  MONDIAL("Mondial", "WC"),
  NATIONAL("National", "NC");

  private final String libelle;
  private final String code;

  ZoneChampionnat(String libelle, String code) {
    this.libelle = libelle;
    this.code = code;
  }

  public static ZoneChampionnat fromCode(String code) {
    return switch (code) {
      case "CC" -> ZoneChampionnat.CONTINENTAL;
      case "JO" -> ZoneChampionnat.JEUX_OLYMPIQUES;
      case "WC" -> ZoneChampionnat.MONDIAL;
      case "NC" -> ZoneChampionnat.NATIONAL;
      default -> throw new IllegalArgumentException("Code de zone de championnat inconnu");
    };
  }

  public String getLibelle() {
    return libelle;
  }

  public String getCode() {
    return code;
  }
}
