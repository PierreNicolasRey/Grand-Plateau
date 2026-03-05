package fr.grand_plateau.administration.infrastructure.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NiveauEquipeRequest(
    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 50)
    String nom,
    @NotBlank(message = "L'abréviation' est obligatoire")
    @Size(min = 2, max = 4)
    String abreviation) {
}
