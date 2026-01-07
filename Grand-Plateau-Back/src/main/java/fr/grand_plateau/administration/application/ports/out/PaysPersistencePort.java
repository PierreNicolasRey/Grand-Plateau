package fr.grand_plateau.administration.application.ports.out;

import fr.grand_plateau.administration.infrastructure.entity.PaysEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaysPersistencePort {
  Optional<PaysEntity> findById(UUID id);
  List<PaysEntity> findAll();
  PaysEntity save(PaysEntity pays);
}
