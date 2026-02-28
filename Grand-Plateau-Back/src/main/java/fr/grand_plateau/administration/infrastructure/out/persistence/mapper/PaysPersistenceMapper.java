package fr.grand_plateau.administration.infrastructure.out.persistence.mapper;

import fr.grand_plateau.administration.domain.model.Pays;
import fr.grand_plateau.administration.infrastructure.out.persistence.entity.PaysEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaysPersistenceMapper {
  Pays toDomain(PaysEntity entity);
  PaysEntity toEntity(Pays domain);
}
