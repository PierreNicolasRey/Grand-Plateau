package fr.grand_plateau.administration.infrastructure.out.persistence.adapter;

import fr.grand_plateau.administration.application.ports.out.PaysRepository;
import fr.grand_plateau.administration.domain.model.Pays;
import fr.grand_plateau.administration.infrastructure.out.persistence.mapper.PaysPersistenceMapper;
import fr.grand_plateau.administration.infrastructure.out.persistence.repository.SpringDataPaysRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PaysPersistenceAdapter implements PaysRepository {

  private final SpringDataPaysRepository jpaRepository;
  private final PaysPersistenceMapper paysPersistenceMapper;

  public PaysPersistenceAdapter(SpringDataPaysRepository jpaRepository, PaysPersistenceMapper persistenceMapper) {
    this.jpaRepository = jpaRepository;
    this.paysPersistenceMapper = persistenceMapper;
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
