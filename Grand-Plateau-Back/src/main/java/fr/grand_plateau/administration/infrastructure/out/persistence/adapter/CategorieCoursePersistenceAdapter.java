package fr.grand_plateau.administration.infrastructure.out.persistence.adapter;

import fr.grand_plateau.administration.application.ports.out.CategorieCourseRepository;
import fr.grand_plateau.administration.domain.model.CategorieCourse;
import fr.grand_plateau.administration.infrastructure.out.persistence.mapper.CategorieCoursePersistenceMapper;
import fr.grand_plateau.administration.infrastructure.out.persistence.repository.SpringDataCategorieCourseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategorieCoursePersistenceAdapter implements CategorieCourseRepository {
  private final SpringDataCategorieCourseRepository jpaRepository;
  private final CategorieCoursePersistenceMapper mapper;

  public CategorieCoursePersistenceAdapter(SpringDataCategorieCourseRepository jpaRepository,
                                           CategorieCoursePersistenceMapper mapper) {
    this.jpaRepository = jpaRepository;
    this.mapper = mapper;
  }

  @Override
  public Optional<CategorieCourse> findById(UUID id) {
    return Optional.empty();
  }

  @Override
  public Optional<CategorieCourse> findByNom(String name) {
    return Optional.empty();
  }

  @Override
  public List<CategorieCourse> findAll() {
    return null;
  }

  @Override
  public CategorieCourse save(CategorieCourse categorieCourse) {
    return null;
  }

  @Override
  public void delete(UUID id) {

  }
}
