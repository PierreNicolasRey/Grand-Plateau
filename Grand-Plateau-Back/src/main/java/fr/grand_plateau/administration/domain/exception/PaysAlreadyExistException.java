package fr.grand_plateau.administration.domain.exception;

public class PaysAlreadyExistException extends RuntimeException {
  public PaysAlreadyExistException(String paysName) {
    super("Le pays " + paysName + " existe déjà en base.");
  }
}
