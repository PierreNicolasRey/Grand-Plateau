package fr.grand_plateau.administration.domain.enums;

public enum NiveauCourse {
  WORLD_TOUR("World Tour", "WT"),
  PRO_SERIES("Pro Series", "PS"),
  CLASSE_1("Classe 1", "I"),
  CLASSE_2("Classe 2", "II"),
  CHAMPIONNAT("Championnat", "C"),
  JEUX_CHPT_INTERNAT("Jeux et Championnats Internationaux", "JR");

  private final String libelle;
  private final String code;

  NiveauCourse(String libelle, String code) {
    this.libelle = libelle;
    this.code = code;
  }

  public String getLibelle() { return libelle; }
  public String getCode() { return code; }

  public static NiveauCourse fromCode(String code) {
    return null;
  }
}
