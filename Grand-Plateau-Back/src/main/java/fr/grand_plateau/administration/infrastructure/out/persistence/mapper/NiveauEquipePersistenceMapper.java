package fr.grand_plateau.administration.infrastructure.out.persistence.mapper;

import fr.grand_plateau.administration.domain.model.NiveauEquipe;
import fr.grand_plateau.administration.infrastructure.out.persistence.entity.NiveauEquipeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NiveauEquipePersistenceMapper {
  NiveauEquipe toDomain(NiveauEquipeEntity entity);
  NiveauEquipeEntity toEntity(NiveauEquipe domain);
}
