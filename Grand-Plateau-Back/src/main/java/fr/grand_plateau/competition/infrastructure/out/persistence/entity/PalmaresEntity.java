package fr.grand_plateau.competition.infrastructure.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

public class PalmaresEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "PALMARES_ID", nullable = false)
  private UUID palmaresId;

  public UUID getPalmaresId() {
    return palmaresId;
  }

  public void setPalmaresId(UUID palmaresId) {
    this.palmaresId = palmaresId;
  }
}
