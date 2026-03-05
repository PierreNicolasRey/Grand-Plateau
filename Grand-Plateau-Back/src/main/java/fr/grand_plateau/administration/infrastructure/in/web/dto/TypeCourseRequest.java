package fr.grand_plateau.administration.infrastructure.in.web.dto;

import fr.grand_plateau.administration.domain.enums.NiveauCourse;
import fr.grand_plateau.administration.domain.enums.PrestigeCourse;
import fr.grand_plateau.administration.domain.enums.ZoneChampionnat;
import jakarta.validation.constraints.NotNull;

public record TypeCourseRequest(
    @NotNull
    NiveauCourse niveauCourse,
    ZoneChampionnat zoneChampionnat,
    Integer duree,
    PrestigeCourse prestigeCourse) {
}
