package fr.grand_plateau.administration.application.ports.out;

import fr.grand_plateau.administration.domain.model.TypeCourse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TypeCourseRepository {
  Optional<TypeCourse> findById(UUID id);
  List<TypeCourse> findAll();
  TypeCourse save(TypeCourse typeCourse);
}
