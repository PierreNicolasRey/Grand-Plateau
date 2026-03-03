package fr.grand_plateau.administration.domain.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) { super(message); }
}
