package fr.grand_plateau.competition.domain.model;

import java.util.UUID;

public record Coureur(UUID coureurId, String nom, String prenom, UUID paysId, Equipe equipe, String anneeElite,
                      String anneePro, long points, Palmares palmares, String dateDeNaissance, String photo) {
}