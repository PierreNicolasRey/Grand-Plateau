package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.in.TypeCourseInputPort;
import fr.grand_plateau.administration.application.ports.out.TypeCourseRepository;
import fr.grand_plateau.administration.domain.exception.TypeCourseNotFoundException;
import fr.grand_plateau.administration.domain.model.TypeCourse;
import fr.grand_plateau.administration.domain.model.TypeCourseRequest;

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
  public TypeCourse save(TypeCourseRequest typeCourseRequest) {
    TypeCourse typeCourseToSave = TypeCourse.create(
        typeCourseRequest.niveauCourse(),
        typeCourseRequest.zoneChampionnat(),
        typeCourseRequest.duree(),
        typeCourseRequest.prestigeCourse()
    );

    return this.typeCourseRepository.save(typeCourseToSave);
  }
}
