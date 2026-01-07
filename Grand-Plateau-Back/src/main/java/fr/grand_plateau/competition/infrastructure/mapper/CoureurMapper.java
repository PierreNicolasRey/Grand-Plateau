package fr.grand_plateau.competition.infrastructure.mapper;

import fr.grand_plateau.administration.application.ports.out.PaysPersistencePort;
import fr.grand_plateau.competition.domain.model.Coureur;
import fr.grand_plateau.competition.infrastructure.entity.CoureurEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = { EquipeMapper.class, PalmaresMapper.class })
public abstract class CoureurMapper {
  @Autowired
  PaysPersistencePort paysPersistencePort;
  @Mapping(target = "pays", expression = "java(paysPersistencePort.findById(domain.paysId()).orElse(null))")
  abstract CoureurEntity toEntity(Coureur domain);

  @Mapping(target = "paysId", source = "pays.paysId")
  abstract Coureur toDomain(CoureurEntity entity);
}
