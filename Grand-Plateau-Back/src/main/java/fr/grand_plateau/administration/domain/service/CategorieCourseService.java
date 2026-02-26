package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.in.CategorieCourseInputPort;
import fr.grand_plateau.administration.application.ports.out.CategorieCourseRepository;
import fr.grand_plateau.administration.domain.model.CategorieCourse;

import java.util.List;
import java.util.UUID;

public class CategorieCourseService implements CategorieCourseInputPort {
  private final CategorieCourseRepository categorieCourseRepository;

  public CategorieCourseService(CategorieCourseRepository categorieCourseRepository) {
    this.categorieCourseRepository = categorieCourseRepository;
  }

  @Override
  public CategorieCourse findById(UUID id) {
    return null;
  }

  @Override
  public List<CategorieCourse> findAll() {
    return null;
  }

  @Override
  public CategorieCourse save(String nom) {
    return null;
  }

  @Override
  public void delete(UUID id) {

  }
}
