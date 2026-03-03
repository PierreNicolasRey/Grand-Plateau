package fr.grand_plateau.administration.domain.exception;

import java.util.UUID;

public class PaysNotFoundException extends ResourceNotFoundException {
  public PaysNotFoundException(UUID paysId) {
    super("Le pays avec l'id : " + paysId.toString() + " n'existe pas en base.");
  }
}
