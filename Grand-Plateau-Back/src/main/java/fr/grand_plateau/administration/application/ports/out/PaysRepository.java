package fr.grand_plateau.administration.application.ports.out;

import fr.grand_plateau.administration.domain.model.Pays;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaysRepository {
  Optional<Pays> findById(UUID id);
  Optional<Pays> findByName(String name);
  List<Pays> findAll();
  Pays save(Pays pays);
  void delete(UUID id);
}
