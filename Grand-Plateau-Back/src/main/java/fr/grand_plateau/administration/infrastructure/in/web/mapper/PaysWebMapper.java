package fr.grand_plateau.administration.infrastructure.in.web.mapper;

import fr.grand_plateau.administration.domain.model.Pays;
import fr.grand_plateau.administration.infrastructure.in.web.dto.PaysDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaysWebMapper {
  Pays toDomain(PaysDTO dto);
  PaysDTO toDTO(Pays domain);
}
