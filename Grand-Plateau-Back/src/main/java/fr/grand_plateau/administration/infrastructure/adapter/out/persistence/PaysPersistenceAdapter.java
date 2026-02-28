package fr.grand_plateau.administration.infrastructure.adapter.out.persistence;

import fr.grand_plateau.administration.application.ports.out.PaysRepository;
import fr.grand_plateau.administration.domain.model.Pays;
import fr.grand_plateau.administration.infrastructure.repository.SpringDataPaysRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PaysPersistenceAdapter implements PaysRepository {

  private final SpringDataPaysRepository jpaRepository;

  public PaysPersistenceAdapter(SpringDataPaysRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }
  @Override
  public Optional<Pays> findById(UUID id) {
    return Optional.empty();
  }

  @Override
  public Optional<Pays> findByNom(String paysName) {
    return Optional.empty();
  }

  @Override
  public List<Pays> findAll() {
    return null;
  }

  @Override
  public Pays save(Pays pays) {
    return null;
  }

  @Override
  public void delete(UUID id) {}
}
