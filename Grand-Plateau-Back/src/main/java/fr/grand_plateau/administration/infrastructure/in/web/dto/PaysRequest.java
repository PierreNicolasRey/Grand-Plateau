package fr.grand_plateau.administration.infrastructure.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PaysRequest(
    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 50)
    String nom,
    @NotBlank(message = "Le code ISO est obligatoire")
    @Size(min = 2, max = 10)
    String codeISO) {}