package fr.grand_plateau.administration.infrastructure.out.persistence.mapper;

import fr.grand_plateau.administration.domain.model.CategorieCourse;
import fr.grand_plateau.administration.infrastructure.out.persistence.entity.CategorieCourseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategorieCoursePersistenceMapper {
  CategorieCourse toDomain(CategorieCourseEntity entity);
  CategorieCourseEntity toEntity(CategorieCourse domain);
}
