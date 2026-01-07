package fr.grand_plateau.competition.infrastructure.entity;

import fr.grand_plateau.administration.infrastructure.entity.PaysEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "COUREUR")
public class CoureurEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "COUREUR_ID", nullable = false)
  private UUID coureurId;

  @Column(name = "NOM", nullable = false)
  private String nom;
  @Column(name = "PRENOM", nullable = false)
  private String prenom;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PAYS_FK", nullable = false)
  private PaysEntity pays;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "EQUIPE_FK")
  private EquipeEntity equipe;
  @Column(name = "ANNEE_ELITE")
  private String anneeElite;
  @Column(name = "ANNEE_PRO")
  private String anneePro;
  @Column(name = "POINTS", nullable = false)
  private long points;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "PALMARES_FK")
  private PalmaresEntity palmares;
  @Column(name = "DATE_DE_NAISSANCE", nullable = false)
  private String dateDeNaissance;
  @Column(name = "PHOTO", nullable = false)
  private String photo;

  public UUID getCoureurId() {
    return coureurId;
  }

  public void setCoureurId(UUID coureurId) {
    this.coureurId = coureurId;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public PaysEntity getPays() {
    return pays;
  }

  public void setPays(PaysEntity pays) {
    this.pays = pays;
  }

  public EquipeEntity getEquipe() {
    return equipe;
  }

  public void setEquipe(EquipeEntity equipe) {
    this.equipe = equipe;
  }

  public String getAnneeElite() {
    return anneeElite;
  }

  public void setAnneeElite(String anneeElite) {
    this.anneeElite = anneeElite;
  }

  public String getAnneePro() {
    return anneePro;
  }

  public void setAnneePro(String anneePro) {
    this.anneePro = anneePro;
  }

  public long getPoints() {
    return points;
  }

  public void setPoints(long points) {
    this.points = points;
  }

  public PalmaresEntity getPalmares() {
    return palmares;
  }

  public void setPalmares(PalmaresEntity palmares) {
    this.palmares = palmares;
  }

  public String getDateDeNaissance() {
    return dateDeNaissance;
  }

  public void setDateDeNaissance(String dateDeNaissance) {
    this.dateDeNaissance = dateDeNaissance;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }
}
