package fr.grand_plateau.administration.infrastructure.in.web.dto;

import fr.grand_plateau.administration.domain.enums.NiveauCourse;
import fr.grand_plateau.administration.domain.enums.PrestigeCourse;
import fr.grand_plateau.administration.domain.enums.ZoneChampionnat;

public record TypeCourseRequest(NiveauCourse niveauCourse,
                                ZoneChampionnat zoneChampionnat,
                                Integer duree,
                                PrestigeCourse prestigeCourse) {
}
