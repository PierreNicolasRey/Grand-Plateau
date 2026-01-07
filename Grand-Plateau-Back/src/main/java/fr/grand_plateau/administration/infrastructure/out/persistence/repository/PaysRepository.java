package fr.grand_plateau.administration.infrastructure.out.persistence.repository;

import fr.grand_plateau.administration.infrastructure.out.persistence.entity.PaysEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaysRepository extends JpaRepository<PaysEntity, UUID> {
}
