package fr.grand_plateau.administration.domain.model;

import java.util.UUID;

public record Pays(UUID paysId, String nom, String codeIso) {
}
