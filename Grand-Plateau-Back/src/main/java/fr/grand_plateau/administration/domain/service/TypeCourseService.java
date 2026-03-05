package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.in.TypeCourseInputPort;
import fr.grand_plateau.administration.application.ports.out.TypeCourseRepository;
import fr.grand_plateau.administration.domain.enums.NiveauCourse;
import fr.grand_plateau.administration.domain.enums.PrestigeCourse;
import fr.grand_plateau.administration.domain.enums.ZoneChampionnat;
import fr.grand_plateau.administration.domain.exception.TypeCourseNotFoundException;
import fr.grand_plateau.administration.domain.model.TypeCourse;

import java.util.List;
import java.util.UUID;

public class TypeCourseService implements TypeCourseInputPort {
  private final TypeCourseRepository typeCourseRepository;

  public TypeCourseService(TypeCourseRepository typeCourseRepository) {
    this.typeCourseRepository = typeCourseRepository;
  }

  @Override
  public TypeCourse findById(UUID id) {
    return this.typeCourseRepository.findById(id).orElseThrow(() -> new TypeCourseNotFoundException(id));
  }

  @Override
  public List<TypeCourse> findAll() {
    return this.typeCourseRepository.findAll();
  }

  @Override
  public TypeCourse save(NiveauCourse niveauCourse,
                         ZoneChampionnat zoneChampionnat,
                         Integer duree,
                         PrestigeCourse prestigeCourse) {
    TypeCourse typeCourseToSave = TypeCourse.create(
        niveauCourse,
        zoneChampionnat,
        duree,
        prestigeCourse
    );

    return this.typeCourseRepository.save(typeCourseToSave);
  }
}
