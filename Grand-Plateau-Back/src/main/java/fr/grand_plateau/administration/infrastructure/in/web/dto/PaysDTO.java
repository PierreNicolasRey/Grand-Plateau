package fr.grand_plateau.administration.infrastructure.in.web.dto;

import java.util.UUID;

public record PaysDTO(UUID paysId, String nom, String codeIso) {
}
