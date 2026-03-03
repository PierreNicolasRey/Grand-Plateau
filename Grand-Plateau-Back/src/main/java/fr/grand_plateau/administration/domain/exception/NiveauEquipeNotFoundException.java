package fr.grand_plateau.administration.domain.exception;

import java.util.UUID;

public class NiveauEquipeNotFoundException extends ResourceNotFoundException {
  public NiveauEquipeNotFoundException(UUID id) {
    super("Le niveau d'équipe avec l'id : " + id + " n'existe pas en base.");
  }
}
