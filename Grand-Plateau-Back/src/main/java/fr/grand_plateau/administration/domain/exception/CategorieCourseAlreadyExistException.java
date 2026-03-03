package fr.grand_plateau.administration.domain.exception;

public class CategorieCourseAlreadyExistException extends ResourceAlreadyExistException {
  public CategorieCourseAlreadyExistException(String nom) {
    super("La catégorie de course nommée " + nom + " existe déjà en base.");
  }
}
