package fr.grand_plateau.competition.infrastructure.entity;

import fr.grand_plateau.administration.infrastructure.out.persistence.entity.PaysEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "EQUIPE")
public class EquipeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "EQUIPE_ID", nullable = false)
  private UUID equipeId;
  @Column(name = "NOM_ABREGE", nullable = false)
  private String nomAbrege;
  @Column(name = "NOM_COMPLET", nullable = false)
  private String nomComplet;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PAYS_FK", nullable = false)
  private PaysEntity pays;
  @Column(name = "NIVEAU", nullable = false)
  private String niveauEquipe;
  @Column(name = "POINTS", nullable = false)
  private long points;

  public UUID getEquipeId() {
    return equipeId;
  }

  public void setEquipeId(UUID equipeId) {
    this.equipeId = equipeId;
  }

  public String getNomAbrege() {
    return nomAbrege;
  }

  public void setNomAbrege(String nomAbrege) {
    this.nomAbrege = nomAbrege;
  }

  public String getNomComplet() {
    return nomComplet;
  }

  public void setNomComplet(String nomComplet) {
    this.nomComplet = nomComplet;
  }

  public PaysEntity getPays() {
    return pays;
  }

  public void setPays(PaysEntity pays) {
    this.pays = pays;
  }

  public String getNiveauEquipe() {
    return niveauEquipe;
  }

  public void setNiveauEquipe(String niveauEquipe) {
    this.niveauEquipe = niveauEquipe;
  }

  public long getPoints() {
    return points;
  }

  public void setPoints(long points) {
    this.points = points;
  }
}
