package fr.grand_plateau.administration.infrastructure.in.web.mapper;

import fr.grand_plateau.administration.domain.model.TypeCourse;
import fr.grand_plateau.administration.infrastructure.in.web.dto.TypeCourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TypeCourseWebMapper {
  TypeCourse toDomain(TypeCourseDTO dto);
  @Mapping(source = "badgeCode", target = "badge")
  TypeCourseDTO toDTO(TypeCourse domain);
}
