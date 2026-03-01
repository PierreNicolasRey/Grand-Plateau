package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.in.NiveauEquipeInputPort;
import fr.grand_plateau.administration.application.ports.out.NiveauEquipeRepository;
import fr.grand_plateau.administration.domain.exception.NiveauEquipeNotFoundException;
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
    return this.niveauEquipeRepository.findById(id).orElseThrow(() -> new NiveauEquipeNotFoundException(id));
  }

  @Override
  public List<NiveauEquipe> findAll() {
    return this.niveauEquipeRepository.findAll();
  }

  @Override
  public NiveauEquipe save(String nom, String abreviation) {
    NiveauEquipe niveauEquipeToSave = NiveauEquipe.create(nom, abreviation);

    return this.niveauEquipeRepository.save(niveauEquipeToSave);
  }
}
