package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.in.TypeCourseInputPort;
import fr.grand_plateau.administration.application.ports.out.TypeCourseRepository;
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
    return null;
  }

  @Override
  public List<TypeCourse> findAll() {
    return null;
  }

  @Override
  public TypeCourse save(TypeCourseRequest typeCourseRequest) {
    return null;
  }
}
