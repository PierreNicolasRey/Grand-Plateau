package fr.grand_plateau.administration.infrastructure.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "NIVEAU_EQUIPE")
public class NiveauEquipeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "NIVEAU_EQUIPE_ID")
  private UUID niveauEquipeId;

  @Column(name = "NOM", nullable = false)
  private String nom;

  @Column(name = "ABREVIATION", nullable = false)
  private String abreviation;

  public UUID getNiveauEquipeId() {
    return niveauEquipeId;
  }

  public void setNiveauEquipeId(UUID niveauEquipeId) {
    this.niveauEquipeId = niveauEquipeId;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getAbreviation() {
    return abreviation;
  }

  public void setAbreviation(String abreviation) {
    this.abreviation = abreviation;
  }
}
