package fr.grand_plateau.administration.application.ports.out;

import fr.grand_plateau.administration.domain.model.CategorieCourse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategorieCourseRepository {
  Optional<CategorieCourse> findById(UUID id);
  Optional<CategorieCourse> findByNom(String name);
  List<CategorieCourse> findAll();
  CategorieCourse save(CategorieCourse categorieCourse);
  void delete(UUID id);
}
