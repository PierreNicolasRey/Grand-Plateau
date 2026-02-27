package fr.grand_plateau.administration.domain.model;

import java.util.UUID;

public record NiveauEquipe(UUID niveauEquipeId, String nom, String abreviation) {
  public NiveauEquipe {

  }
  public static NiveauEquipe create(String nom, String abreviation) {
    validateArguments(nom, abreviation);
    return null;
  }

  private static void validateArguments(String nom, String abreviation) {

  }
}
