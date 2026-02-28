package fr.grand_plateau.administration.infrastructure.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "CATEGORIE_COURSE")
public class CategorieCourseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "CATEGORIE_COURSE_ID", nullable = false)
  private UUID categorieCourseId;

  @Column(name = "NOM", nullable = false)
  private String nom;

  @Column(name = "ABREVIATION", nullable = false)
  private String abreviation;

  public UUID getCategorieCourseId() {
    return categorieCourseId;
  }

  public void setCategorieCourseId(UUID categorieCourseId) {
    this.categorieCourseId = categorieCourseId;
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
