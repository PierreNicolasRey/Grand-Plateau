package fr.grand_plateau.administration.infrastructure.out.persistence.adapter;

import fr.grand_plateau.administration.application.ports.out.TypeCourseRepository;
import fr.grand_plateau.administration.domain.model.TypeCourse;
import fr.grand_plateau.administration.infrastructure.out.persistence.entity.TypeCourseEntity;
import fr.grand_plateau.administration.infrastructure.out.persistence.mapper.TypeCoursePersistenceMapper;
import fr.grand_plateau.administration.infrastructure.out.persistence.repository.SpringDataTypeCourseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TypeCoursePersistenceAdapter implements TypeCourseRepository {
  private final SpringDataTypeCourseRepository typeCourseRepository;
  private final TypeCoursePersistenceMapper mapper;

  public TypeCoursePersistenceAdapter(SpringDataTypeCourseRepository typeCourseRepository,
                                      TypeCoursePersistenceMapper mapper) {
    this.typeCourseRepository = typeCourseRepository;
    this.mapper = mapper;
  }

  @Override
  public Optional<TypeCourse> findById(UUID id) {
    return this.typeCourseRepository.findById(id).map(this.mapper::toDomain);
  }

  @Override
  public List<TypeCourse> findAll() {
    return this.typeCourseRepository.findAll().stream().map(this.mapper::toDomain).toList();
  }

  @Override
  public TypeCourse save(TypeCourse typeCourse) {
    TypeCourseEntity entitySaved = this.typeCourseRepository.save(this.mapper.toEntity(typeCourse));

    return this.mapper.toDomain(entitySaved);
  }
}
