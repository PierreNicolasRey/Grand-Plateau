package fr.grand_plateau.administration.infrastructure.out.persistence.adapter;

import fr.grand_plateau.administration.domain.enums.NiveauCourse;
import fr.grand_plateau.administration.domain.enums.PrestigeCourse;
import fr.grand_plateau.administration.domain.enums.ZoneChampionnat;
import fr.grand_plateau.administration.domain.model.TypeCourse;
import fr.grand_plateau.administration.infrastructure.out.persistence.entity.TypeCourseEntity;
import fr.grand_plateau.administration.infrastructure.out.persistence.mapper.TypeCoursePersistenceMapper;
import fr.grand_plateau.administration.infrastructure.out.persistence.repository.SpringDataTypeCourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TypeCoursePersistenceAdapterTest {
  @Mock
  private SpringDataTypeCourseRepository repository;
  private final TypeCoursePersistenceMapper mapper = Mappers.getMapper(TypeCoursePersistenceMapper.class);
  TypeCoursePersistenceAdapter sut;

  @BeforeEach
  void setUp() {
    this.sut = new TypeCoursePersistenceAdapter(repository, mapper);
  }

  @Test
  void should_find_by_id_successfully_for_world_tour() {
    // GIVEN - Cas avec durée et prestige sans zone
    UUID id = UUID.randomUUID();
    TypeCourse domain = new TypeCourse(id, NiveauCourse.WORLD_TOUR, null, 1, PrestigeCourse.A);
    TypeCourseEntity entity = mapper.toEntity(domain);

    when(repository.findById(id)).thenReturn(Optional.of(entity));

    // WHEN
    Optional<TypeCourse> result = sut.findById(id);

    // THEN
    Assertions.assertAll(() -> {
      assertTrue(result.isPresent());
      assertEquals(NiveauCourse.WORLD_TOUR, result.get().niveauCourse());
      assertEquals(PrestigeCourse.A, result.get().prestigeCourse());
      assertEquals(1, result.get().duree());
      assertNull(result.get().zoneChampionnat());
    });
  }

  @Test
  void should_find_by_id_successfully_for_championnat() {
    // GIVEN - Cas avec Zone, sans durée
    UUID id = UUID.randomUUID();
    TypeCourse domain = new TypeCourse(id, NiveauCourse.CHAMPIONNAT, ZoneChampionnat.NATIONAL, null, PrestigeCourse.B);
    TypeCourseEntity entity = mapper.toEntity(domain);

    when(repository.findById(id)).thenReturn(Optional.of(entity));

    // WHEN
    Optional<TypeCourse> result = sut.findById(id);

    // THEN
    Assertions.assertAll(() -> {
      assertTrue(result.isPresent());
      assertEquals(ZoneChampionnat.NATIONAL, result.get().zoneChampionnat());
      assertNull(result.get().duree());
      assertEquals(PrestigeCourse.B, result.get().prestigeCourse());
    });
  }

  @Test
  void should_return_empty_when_not_found() {
    // GIVEN
    UUID id = UUID.randomUUID();
    when(repository.findById(id)).thenReturn(Optional.empty());

    // WHEN
    Optional<TypeCourse> result = sut.findById(id);

    // THEN
    assertFalse(result.isPresent());
  }

  @Test
  void should_find_all_successfully() {
    // GIVEN
    TypeCourse domain1 =
        new TypeCourse(UUID.randomUUID(), NiveauCourse.WORLD_TOUR, null, 1, PrestigeCourse.A);
    TypeCourse domain2 =
        new TypeCourse(UUID.randomUUID(), NiveauCourse.PRO_SERIES, null, 2, null);

    List<TypeCourseEntity> entities = List.of(mapper.toEntity(domain1), mapper.toEntity(domain2));
    when(repository.findAll()).thenReturn(entities);

    // WHEN
    List<TypeCourse> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(2, result.size());
      assertEquals(NiveauCourse.WORLD_TOUR, result.get(0).niveauCourse());
      assertEquals(NiveauCourse.PRO_SERIES, result.get(1).niveauCourse());
    });
  }

  @Test
  void should_return_empty_liste_when_no_TypeCourse_found() {
    // GIVEN
    when(repository.findAll()).thenReturn(List.of());

    // WHEN
    List<TypeCourse> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(0, result.size());
    });
  }

  @Test
  void should_save_successfully() {
    // GIVEN
    TypeCourse domainToSave =
        new TypeCourse(UUID.randomUUID(), NiveauCourse.CLASSE_1, null, 1, null);
    TypeCourseEntity entityToReturn = mapper.toEntity(domainToSave);

    when(repository.save(any(TypeCourseEntity.class))).thenReturn(entityToReturn);

    // WHEN
    TypeCourse result = sut.save(domainToSave);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(NiveauCourse.CLASSE_1, result.niveauCourse());
      assertEquals(1, result.duree());
      verify(repository, times(1)).save(any(TypeCourseEntity.class));
    });
  }
}