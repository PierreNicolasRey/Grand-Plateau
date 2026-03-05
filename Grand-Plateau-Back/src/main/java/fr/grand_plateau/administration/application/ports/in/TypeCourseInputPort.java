package fr.grand_plateau.administration.application.ports.in;

import fr.grand_plateau.administration.domain.enums.NiveauCourse;
import fr.grand_plateau.administration.domain.enums.PrestigeCourse;
import fr.grand_plateau.administration.domain.enums.ZoneChampionnat;
import fr.grand_plateau.administration.domain.model.TypeCourse;

import java.util.List;
import java.util.UUID;

public interface TypeCourseInputPort {
  TypeCourse findById(UUID id);
  List<TypeCourse> findAll();

  TypeCourse save(NiveauCourse niveauCourse,
                  ZoneChampionnat zoneChampionnat,
                  Integer duree,
                  PrestigeCourse prestigeCourse);
}
