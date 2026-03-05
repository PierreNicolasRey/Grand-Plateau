package fr.grand_plateau.administration.infrastructure.in.web.mapper;

import fr.grand_plateau.administration.domain.model.CategorieCourse;
import fr.grand_plateau.administration.infrastructure.in.web.dto.CategorieCourseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategorieCourseWebMapper {
  CategorieCourse toDomain(CategorieCourseDTO dto);
  CategorieCourseDTO toDTO(CategorieCourse domain);
}
