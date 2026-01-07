package fr.grand_plateau.administration.infrastructure.repository;

import fr.grand_plateau.administration.infrastructure.entity.PaysEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataPaysRepository extends JpaRepository<PaysEntity, UUID> {
}
