package fr.grand_plateau.administration.infrastructure.out.persistence.entity;

import fr.grand_plateau.administration.domain.enums.NiveauCourse;
import fr.grand_plateau.administration.domain.enums.PrestigeCourse;
import fr.grand_plateau.administration.domain.enums.ZoneChampionnat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "TYPE_COURSE")
public class TypeCourseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "TYPE_COURSE_ID", nullable = false)
  private UUID typeCourseId;

  @Column(name = "NIVEAU_COURSE", nullable = false)
  @Enumerated(EnumType.STRING)
  private NiveauCourse niveauCourse;

  @Column(name = "ZONE_CHAMPIONNAT")
  @Enumerated(EnumType.STRING)
  private ZoneChampionnat zoneChampionnat;

  @Column(name = "DUREE")
  private Integer duree;

  @Column(name = "PRESTIGE")
  @Enumerated(EnumType.STRING)
  private PrestigeCourse prestigeCourse;

  public UUID getTypeCourseId() {
    return typeCourseId;
  }

  public void setTypeCourseId(UUID typeCourseId) {
    this.typeCourseId = typeCourseId;
  }

  public NiveauCourse getNiveauCourse() {
    return niveauCourse;
  }

  public void setNiveauCourse(NiveauCourse niveauCourse) {
    this.niveauCourse = niveauCourse;
  }

  public ZoneChampionnat getZoneChampionnat() {
    return zoneChampionnat;
  }

  public void setZoneChampionnat(ZoneChampionnat zoneChampionnat) {
    this.zoneChampionnat = zoneChampionnat;
  }

  public Integer getDuree() {
    return duree;
  }

  public void setDuree(Integer duree) {
    this.duree = duree;
  }

  public PrestigeCourse getPrestigeCourse() {
    return prestigeCourse;
  }

  public void setPrestigeCourse(PrestigeCourse prestigeCourse) {
    this.prestigeCourse = prestigeCourse;
  }
}
