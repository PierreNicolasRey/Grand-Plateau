package fr.grand_plateau.administration.infrastructure.out.persistence.repository;

import fr.grand_plateau.administration.infrastructure.out.persistence.entity.NiveauEquipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataNiveauEquipeRepository extends JpaRepository<NiveauEquipeEntity, UUID> {
}
