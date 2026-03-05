package fr.grand_plateau.administration.infrastructure.in.web.mapper;

import fr.grand_plateau.administration.domain.model.NiveauEquipe;
import fr.grand_plateau.administration.infrastructure.in.web.dto.NiveauEquipeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NiveauEquipeWebMapper {
  NiveauEquipe toDomain(NiveauEquipeDTO dto);
  NiveauEquipeDTO toDTO(NiveauEquipe domain);
}
