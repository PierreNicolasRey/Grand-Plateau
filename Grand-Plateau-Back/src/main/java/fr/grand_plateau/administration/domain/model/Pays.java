package fr.grand_plateau.administration.domain.model;

import java.util.Objects;
import java.util.UUID;

public record Pays(UUID paysId, String nom, String codeIso) {
  public Pays {
    Objects.requireNonNull(paysId);
    Objects.requireNonNull(nom);
    Objects.requireNonNull(codeIso);
  }
  public static Pays create(String nom, String codeIso) {
    validateArguments(nom, codeIso);

    return new Pays(
        UUID.randomUUID(),
        formatNom(nom),
        codeIso.trim().toUpperCase()
    );
  }

  private static void validateArguments(String nom, String codeIso) {
    if (nom == null || nom.isBlank()) {
      throw new IllegalArgumentException("Le nom est requis");
    }
    if (codeIso == null || codeIso.isBlank() || codeIso.trim().length() != 2) {
      throw new IllegalArgumentException("Le code ISO doit faire 2 caractères");
    }
  }

  private static String formatNom(String nom) {
    char[] chars = nom.trim().toLowerCase().toCharArray();
    boolean foundSeparator = true;

    for (int i = 0; i < chars.length; i++) {
      if (Character.isLetter(chars[i])) {
        if (foundSeparator) {
          chars[i] = Character.toUpperCase(chars[i]);
          foundSeparator = false;
        }
      } else if (chars[i] == ' ' || chars[i] == '-') {
        foundSeparator = true;
      }
    }
    return new String(chars);
  }
}
