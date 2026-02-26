package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.in.PaysInputPort;
import fr.grand_plateau.administration.domain.model.Pays;

import java.util.List;
import java.util.UUID;

public class PaysService implements PaysInputPort {
  @Override
  public Pays findById(UUID id) {
    return null;
  }

  @Override
  public List<Pays> findAll() {
    return null;
  }

  @Override
  public Pays save(String nom) {
    return null;
  }

  @Override
  public void delete(UUID id) {

  }
}
