package fr.grand_plateau.competition.infrastructure.mapper;

import fr.grand_plateau.administration.infrastructure.out.persistence.repository.PaysRepository;
import fr.grand_plateau.competition.domain.model.Equipe;
import fr.grand_plateau.competition.infrastructure.out.persistence.entity.EquipeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class EquipeMapper {
  @Autowired
  PaysRepository paysRepository;

  @Mapping(target = "pays", expression = "java(paysRepository.findById(domain.paysId()).orElse(null))")
  abstract EquipeEntity toEntity(Equipe domain);

  @Mapping(target = "paysId", source = "pays.paysId")
  abstract Equipe toDomain(EquipeEntity entity);
}
