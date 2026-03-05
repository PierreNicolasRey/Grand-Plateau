package fr.grand_plateau.administration.infrastructure.in.web.dto;

import java.util.UUID;

public record CategorieCourseDTO(UUID categorieCourseId, String nom, String abreviation) {}
