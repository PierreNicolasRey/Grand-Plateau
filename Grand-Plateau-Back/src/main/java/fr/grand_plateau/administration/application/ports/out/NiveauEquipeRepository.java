package fr.grand_plateau.administration.application.ports.out;

import fr.grand_plateau.administration.domain.model.NiveauEquipe;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NiveauEquipeRepository {
  Optional<NiveauEquipe> findById(UUID id);
  List<NiveauEquipe> findAll();
  NiveauEquipe save(NiveauEquipe niveauEquipe);
}
