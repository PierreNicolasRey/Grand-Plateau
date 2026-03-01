package fr.grand_plateau.administration.infrastructure.out.persistence.mapper;

import fr.grand_plateau.administration.domain.model.TypeCourse;
import fr.grand_plateau.administration.infrastructure.out.persistence.entity.TypeCourseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeCoursePersistenceMapper {
  TypeCourse toDomain(TypeCourseEntity entity);
  TypeCourseEntity toEntity(TypeCourse domain);
}
