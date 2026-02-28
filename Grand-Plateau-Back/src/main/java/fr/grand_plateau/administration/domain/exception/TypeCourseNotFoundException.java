package fr.grand_plateau.administration.domain.exception;

import java.util.UUID;

public class TypeCourseNotFoundException extends RuntimeException {
  public TypeCourseNotFoundException(UUID id) {
    super("Le type de course avec l'id : " + id.toString() + " n'existe pas en base.");
  }
}
