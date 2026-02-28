package fr.grand_plateau.administration.infrastructure.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "PAYS")
public class PaysEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "PAYS_ID", nullable = false)
  private UUID paysId;
  @Column(name = "NOM", nullable = false)
  private String nom;

  @Column(name = "CODE_ISO", nullable = false)
  private String codeIso;

  public UUID getPaysId() {
    return paysId;
  }

  public void setPaysId(UUID paysId) {
    this.paysId = paysId;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getCodeIso() {
    return codeIso;
  }

  public void setCodeIso(String codeIso) {
    this.codeIso = codeIso;
  }
}
