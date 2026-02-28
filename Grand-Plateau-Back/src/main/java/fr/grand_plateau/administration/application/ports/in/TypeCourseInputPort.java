package fr.grand_plateau.administration.application.ports.in;

import fr.grand_plateau.administration.domain.model.TypeCourse;
import fr.grand_plateau.administration.domain.model.TypeCourseRequest;

import java.util.List;
import java.util.UUID;

public interface TypeCourseInputPort {
  TypeCourse findById(UUID id);
  List<TypeCourse> findAll();

  TypeCourse save(TypeCourseRequest typeCourseRequest);
}
