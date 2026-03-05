package fr.grand_plateau.administration.infrastructure.in.web.dto;

import java.util.UUID;

public record NiveauEquipeDTO(UUID niveauEquipeId, String nom, String abreviation) {
}
