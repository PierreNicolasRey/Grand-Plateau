package fr.grand_plateau.administration.infrastructure.out.persistence.adapter;

import fr.grand_plateau.administration.domain.model.CategorieCourse;
import fr.grand_plateau.administration.infrastructure.out.persistence.entity.CategorieCourseEntity;
import fr.grand_plateau.administration.infrastructure.out.persistence.mapper.CategorieCoursePersistenceMapper;
import fr.grand_plateau.administration.infrastructure.out.persistence.repository.SpringDataCategorieCourseRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategorieCoursePersistenceAdapterTest {
  @Mock
  private SpringDataCategorieCourseRepository repository;

  private final CategorieCoursePersistenceMapper mapper = Mappers.getMapper(CategorieCoursePersistenceMapper.class);

  CategorieCoursePersistenceAdapter sut;

  CategorieCoursePersistenceAdapterTest() {
  }

  @BeforeEach
  void setUp() {
    this.sut = new CategorieCoursePersistenceAdapter(repository, mapper);
  }

  @Test
  void should_find_by_id_successfully() {
    // GIVEN
    UUID id = UUID.randomUUID();
    CategorieCourse domain = new CategorieCourse(id, "Catégorie 1", "C1");
    CategorieCourseEntity entity = mapper.toEntity(domain);

    when(repository.findById(id)).thenReturn(Optional.of(entity));

    // WHEN
    Optional<CategorieCourse> result = sut.findById(id);

    // THEN
    Assertions.assertAll(() -> {
      assertTrue(result.isPresent());
      assertEquals(domain, result.get());
    });
  }

  @Test
  void should_return_empty_when_not_found_by_id() {
    // GIVEN
    UUID id = UUID.randomUUID();
    when(repository.findById(id)).thenReturn(Optional.empty());

    // WHEN
    Optional<CategorieCourse> result = sut.findById(id);

    // THEN
    assertFalse(result.isPresent());
  }

  @Test
  void should_find_by_nom_successfully() {
    // GIVEN
    String nom = "Catégorie 13";
    CategorieCourse domain = new CategorieCourse(UUID.randomUUID(), nom, "C13");
    CategorieCourseEntity entity = mapper.toEntity(domain);

    when(repository.findByNom(nom)).thenReturn(Optional.of(entity));

    // WHEN
    Optional<CategorieCourse> result = sut.findByNom(nom);

    // THEN
    Assertions.assertAll(() -> {
      assertTrue(result.isPresent());
      assertEquals(nom, result.get().nom());
    });
  }

  @Test
  void should_return_empty_when_not_found_by_name() {
    // GIVEN
    UUID id = UUID.randomUUID();
    when(repository.findById(id)).thenReturn(Optional.empty());

    // WHEN
    Optional<CategorieCourse> result = sut.findById(id);

    // THEN
    assertFalse(result.isPresent());
  }

  @Test
  void should_find_all_successfully() {
    // GIVEN
    CategorieCourseEntity entity1 = new CategorieCourseEntity();
    entity1.setCategorieCourseId(UUID.randomUUID());
    entity1.setNom("Catégorie 2");
    entity1.setAbreviation("C2");

    CategorieCourseEntity entity2 = new CategorieCourseEntity();
    entity2.setCategorieCourseId(UUID.randomUUID());
    entity2.setNom("Catégorie 10");
    entity2.setAbreviation("C10");

    when(repository.findAll()).thenReturn(List.of(entity1, entity2));

    // WHEN
    List<CategorieCourse> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(2, result.size());
      assertEquals("Catégorie 2", result.get(0).nom());
      assertEquals("C2", result.get(0).abreviation());
    });
  }

  @Test
  void should_save_successfully() {
    // GIVEN
    CategorieCourse domainToSave = CategorieCourse.create("Catégorie 3");
    CategorieCourseEntity entityToReturn = mapper.toEntity(domainToSave);

    when(repository.save(any(CategorieCourseEntity.class))).thenReturn(entityToReturn);

    // WHEN
    CategorieCourse result = sut.save(domainToSave);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(domainToSave.nom(), result.nom());
      verify(repository, times(1)).save(any(CategorieCourseEntity.class));
    });
  }

  @Test
  void should_delete_successfully() {
    // GIVEN
    UUID id = UUID.randomUUID();

    // WHEN
    sut.delete(id);

    // THEN
    verify(repository, times(1)).deleteById(id);
  }
}