package fr.grand_plateau.administration.domain.exception;

import java.util.UUID;

public class CategorieCourseNotFoundException extends RuntimeException {
  public CategorieCourseNotFoundException(UUID id) {
    super("La catégorie de course portant l'id : " + id + " n'existe pas en base.");
  }
}
