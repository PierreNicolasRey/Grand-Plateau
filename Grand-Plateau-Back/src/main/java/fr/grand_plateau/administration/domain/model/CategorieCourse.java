package fr.grand_plateau.administration.domain.model;

import java.util.Objects;
import java.util.UUID;

public record CategorieCourse(UUID categorieCourseId, String nom, String abreviation) {
  public CategorieCourse {
    Objects.requireNonNull(categorieCourseId);
    Objects.requireNonNull(nom);
    Objects.requireNonNull(abreviation);
  }
  public static CategorieCourse create(String nom) {
    validateArguments(nom);
    String[] nomSplitted = nom.split(" ");
    String abreviation = nomSplitted[0].charAt(0) + nomSplitted[1];

    return new CategorieCourse(
        UUID.randomUUID(),
        nom.trim(),
        abreviation);
  }

  private static void validateArguments(String nom) {
    if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Le nom est requis");
  }
}
