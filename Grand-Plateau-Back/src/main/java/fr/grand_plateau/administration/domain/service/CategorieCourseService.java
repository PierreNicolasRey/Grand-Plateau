package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.in.CategorieCourseInputPort;
import fr.grand_plateau.administration.application.ports.out.CategorieCourseRepository;
import fr.grand_plateau.administration.domain.exception.CategorieCourseAlreadyExistException;
import fr.grand_plateau.administration.domain.exception.CategorieCourseNotFoundException;
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
    return this.categorieCourseRepository.findById(id).orElseThrow(() -> new CategorieCourseNotFoundException(id));
  }

  @Override
  public List<CategorieCourse> findAll() {
    return this.categorieCourseRepository.findAll();
  }

  @Override
  public CategorieCourse save(String nom) {
    if (this.categorieCourseRepository.findByName(nom).isPresent()) {
      throw new CategorieCourseAlreadyExistException(nom);
    }

    CategorieCourse categorieCourseToSave = CategorieCourse.create(nom);

    return this.categorieCourseRepository.save(categorieCourseToSave);
  }

  @Override
  public void delete(UUID id) {
    if (this.categorieCourseRepository.findById(id).isEmpty()) {
      throw new CategorieCourseNotFoundException(id);
    }

    this.categorieCourseRepository.delete(id);
  }
}
