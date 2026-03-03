package fr.grand_plateau.administration.domain.exception;

public class ResourceAlreadyExistException extends RuntimeException {
  public ResourceAlreadyExistException(String message) { super(message); }
}
