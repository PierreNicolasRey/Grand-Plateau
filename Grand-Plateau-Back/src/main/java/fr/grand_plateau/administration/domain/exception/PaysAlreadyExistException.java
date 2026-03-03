package fr.grand_plateau.administration.domain.exception;

public class PaysAlreadyExistException extends ResourceAlreadyExistException {
  public PaysAlreadyExistException(String paysName) {
    super("Le pays " + paysName + " existe déjà en base.");
  }
}
