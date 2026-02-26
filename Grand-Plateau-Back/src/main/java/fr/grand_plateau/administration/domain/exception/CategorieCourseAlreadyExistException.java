package fr.grand_plateau.administration.domain.exception;

public class CategorieCourseAlreadyExistException extends RuntimeException {
  public CategorieCourseAlreadyExistException(String nom) {
    super("La catégorie de course nommée " + nom + " existe déjà en base.");
  }
}
