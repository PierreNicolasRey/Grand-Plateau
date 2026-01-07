package fr.grand_plateau.administration.infrastructure.adapter.out.persistence;

import fr.grand_plateau.administration.application.ports.out.PaysPersistencePort;
import fr.grand_plateau.administration.infrastructure.entity.PaysEntity;
import fr.grand_plateau.administration.infrastructure.repository.SpringDataPaysRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PaysPersistenceAdapter implements PaysPersistencePort {

  private final SpringDataPaysRepository jpaRepository;

  public PaysPersistenceAdapter(SpringDataPaysRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }
  @Override
  public Optional<PaysEntity> findById(UUID id) {
    return Optional.empty();
  }

  @Override
  public List<PaysEntity> findAll() {
    return null;
  }

  @Override
  public PaysEntity save(PaysEntity pays) {
    return null;
  }
}
