package fr.grand_plateau.administration.domain.model;

import java.util.UUID;

public record Pays(UUID paysId, String nom, String codeIso) {
  public static Pays create(String nom, String codeIso) {
    return null;
  }
}
