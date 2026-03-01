package fr.grand_plateau.administration.infrastructure.out.persistence.adapter;

import fr.grand_plateau.administration.application.ports.out.NiveauEquipeRepository;
import fr.grand_plateau.administration.domain.model.NiveauEquipe;
import fr.grand_plateau.administration.infrastructure.out.persistence.mapper.NiveauEquipePersistenceMapper;
import fr.grand_plateau.administration.infrastructure.out.persistence.repository.SpringDataNiveauEquipeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class NiveauEquipePersistenceAdapter implements NiveauEquipeRepository {
  private final SpringDataNiveauEquipeRepository repository;
  private final NiveauEquipePersistenceMapper mapper;

  public NiveauEquipePersistenceAdapter(SpringDataNiveauEquipeRepository repository, NiveauEquipePersistenceMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Optional<NiveauEquipe> findById(UUID id) {
    return Optional.empty();
  }

  @Override
  public List<NiveauEquipe> findAll() {
    return null;
  }

  @Override
  public NiveauEquipe save(NiveauEquipe niveauEquipe) {
    return null;
  }
}
