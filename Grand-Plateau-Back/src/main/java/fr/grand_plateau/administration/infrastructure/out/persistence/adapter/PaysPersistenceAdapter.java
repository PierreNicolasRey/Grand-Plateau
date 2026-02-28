package fr.grand_plateau.administration.infrastructure.out.persistence.adapter;

import fr.grand_plateau.administration.application.ports.out.PaysRepository;
import fr.grand_plateau.administration.domain.model.Pays;
import fr.grand_plateau.administration.infrastructure.out.persistence.entity.PaysEntity;
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
    return this.jpaRepository.findById(id).map(this.paysPersistenceMapper::toDomain);
  }

  @Override
  public Optional<Pays> findByNom(String paysName) {
    return this.jpaRepository.findByNom(paysName).map(this.paysPersistenceMapper::toDomain);
  }

  @Override
  public List<Pays> findAll() {
    return this.jpaRepository.findAll().stream().map(this.paysPersistenceMapper::toDomain).toList();
  }

  @Override
  public Pays save(Pays pays) {
    PaysEntity paysSaved = this.jpaRepository.save(this.paysPersistenceMapper.toEntity(pays));
    return this.paysPersistenceMapper.toDomain(paysSaved);
  }

  @Override
  public void delete(UUID id) {
    this.jpaRepository.deleteById(id);
  }
}
