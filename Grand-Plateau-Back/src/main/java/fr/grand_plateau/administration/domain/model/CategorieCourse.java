package fr.grand_plateau.administration.domain.model;

import java.util.UUID;

public record CategorieCourse(UUID categorieCourseId, String nom, String abreviation) {
  public static CategorieCourse create(String nom) {
    validateArguments(nom);

    return null;
  }

  private static void validateArguments(String nom) {

  }
}
