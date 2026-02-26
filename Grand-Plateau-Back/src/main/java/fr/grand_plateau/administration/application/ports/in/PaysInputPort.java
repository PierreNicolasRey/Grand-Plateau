package fr.grand_plateau.administration.application.ports.in;

import fr.grand_plateau.administration.domain.model.Pays;

import java.util.List;
import java.util.UUID;

public interface PaysInputPort {
  Pays findById(UUID id);
  List<Pays> findAll();
  Pays save(String nom);
  void delete(UUID id);
}
