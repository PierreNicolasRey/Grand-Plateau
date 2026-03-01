package fr.grand_plateau.administration.application.ports.in;

import fr.grand_plateau.administration.domain.model.NiveauEquipe;

import java.util.List;
import java.util.UUID;

public interface NiveauEquipeInputPort {
  NiveauEquipe findById(UUID id);
  List<NiveauEquipe> findAll();
  NiveauEquipe save(String nom, String abreviation);
}
