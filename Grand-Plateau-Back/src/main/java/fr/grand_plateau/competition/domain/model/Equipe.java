package fr.grand_plateau.competition.domain.model;

import java.util.UUID;

public record Equipe(UUID equipeId, String nomAbrege, String nomComplet, UUID paysId, String niveauEquipe,
                     long points) {
}
