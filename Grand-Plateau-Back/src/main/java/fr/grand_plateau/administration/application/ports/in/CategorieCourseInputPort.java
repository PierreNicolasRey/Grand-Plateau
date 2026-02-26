package fr.grand_plateau.administration.application.ports.in;

import fr.grand_plateau.administration.domain.model.CategorieCourse;

import java.util.List;
import java.util.UUID;

public interface CategorieCourseInputPort {
  CategorieCourse findById(UUID id);
  List<CategorieCourse> findAll();
  CategorieCourse save(String nom);
  void delete(UUID id);
}
