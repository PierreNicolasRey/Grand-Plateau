package fr.grand_plateau.administration.infrastructure.out.persistence.adapter;

import fr.grand_plateau.administration.application.ports.out.CategorieCourseRepository;
import fr.grand_plateau.administration.domain.model.CategorieCourse;
import fr.grand_plateau.administration.infrastructure.out.persistence.entity.CategorieCourseEntity;
import fr.grand_plateau.administration.infrastructure.out.persistence.mapper.CategorieCoursePersistenceMapper;
import fr.grand_plateau.administration.infrastructure.out.persistence.repository.SpringDataCategorieCourseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
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
    return this.jpaRepository.findById(id).map(this.mapper::toDomain);
  }

  @Override
  public Optional<CategorieCourse> findByNom(String name) {
    return this.jpaRepository.findByNom(name).map(this.mapper::toDomain);
  }

  @Override
  public List<CategorieCourse> findAll() {
    return this.jpaRepository.findAll().stream().map(this.mapper::toDomain).toList();
  }

  @Override
  public CategorieCourse save(CategorieCourse categorieCourse) {
    CategorieCourseEntity entitySaved = this.jpaRepository.save(this.mapper.toEntity(categorieCourse));
    return this.mapper.toDomain(entitySaved);
  }

  @Override
  public void delete(UUID id) {
    this.jpaRepository.deleteById(id);
  }
}
