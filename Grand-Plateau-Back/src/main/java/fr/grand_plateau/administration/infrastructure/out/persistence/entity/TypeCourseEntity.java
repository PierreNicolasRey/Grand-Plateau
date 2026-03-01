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
}
