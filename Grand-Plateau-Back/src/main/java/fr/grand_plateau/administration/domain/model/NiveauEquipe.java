package fr.grand_plateau.administration.domain.model;

import java.util.Objects;
import java.util.UUID;

public record NiveauEquipe(UUID niveauEquipeId, String nom, String abreviation) {
  public NiveauEquipe {
    Objects.requireNonNull(niveauEquipeId);
    Objects.requireNonNull(nom);
    Objects.requireNonNull(abreviation);
  }
  public static NiveauEquipe create(String nom, String abreviation) {
    validateArguments(nom, abreviation);
    return new NiveauEquipe(
        UUID.randomUUID(),
        nom.trim().toUpperCase(),
        abreviation.trim().toUpperCase()
    );
  }

  private static void validateArguments(String nom, String abreviation) {
    if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Le nom est requis");
    if (abreviation == null || abreviation.isBlank()) throw new IllegalArgumentException("L'abréviation est requise");
  }
}
