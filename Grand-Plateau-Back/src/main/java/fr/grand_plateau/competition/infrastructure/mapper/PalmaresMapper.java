package fr.grand_plateau.competition.infrastructure.mapper;

import fr.grand_plateau.competition.domain.model.Palmares;
import fr.grand_plateau.competition.infrastructure.entity.PalmaresEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PalmaresMapper {

  abstract PalmaresEntity toEntity(Palmares domain);

  abstract Palmares toDomain(PalmaresEntity entity);
}
