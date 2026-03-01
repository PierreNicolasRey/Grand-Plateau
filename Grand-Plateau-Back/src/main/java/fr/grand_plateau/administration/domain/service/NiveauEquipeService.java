package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.in.NiveauEquipeInputPort;
import fr.grand_plateau.administration.application.ports.out.NiveauEquipeRepository;
import fr.grand_plateau.administration.domain.model.NiveauEquipe;

import java.util.List;
import java.util.UUID;

public class NiveauEquipeService implements NiveauEquipeInputPort {
  private final NiveauEquipeRepository niveauEquipeRepository;

  public NiveauEquipeService(NiveauEquipeRepository niveauEquipeRepository) {
    this.niveauEquipeRepository = niveauEquipeRepository;
  }

  @Override
  public NiveauEquipe findById(UUID id) {
    return null;
  }

  @Override
  public List<NiveauEquipe> findAll() {
    return null;
  }

  @Override
  public NiveauEquipe save(String nom, String abreviation) {
    return null;
  }
}
