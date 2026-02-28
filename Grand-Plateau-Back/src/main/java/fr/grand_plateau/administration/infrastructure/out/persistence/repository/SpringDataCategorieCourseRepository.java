package fr.grand_plateau.administration.infrastructure.out.persistence.repository;

import fr.grand_plateau.administration.infrastructure.out.persistence.entity.CategorieCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataCategorieCourseRepository extends JpaRepository<CategorieCourseEntity, UUID> {
  Optional<CategorieCourseEntity> findByNom(String nom);
}
