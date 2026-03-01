package fr.grand_plateau.administration.infrastructure.out.persistence.adapter;

import fr.grand_plateau.administration.domain.model.NiveauEquipe;
import fr.grand_plateau.administration.infrastructure.out.persistence.entity.NiveauEquipeEntity;
import fr.grand_plateau.administration.infrastructure.out.persistence.mapper.NiveauEquipePersistenceMapper;
import fr.grand_plateau.administration.infrastructure.out.persistence.repository.SpringDataNiveauEquipeRepository;
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
public class NiveauEquipePersistenceAdapterTest {
  @Mock
  private SpringDataNiveauEquipeRepository repository;
  private NiveauEquipePersistenceMapper mapper = Mappers.getMapper(NiveauEquipePersistenceMapper.class);

  NiveauEquipePersistenceAdapter sut;

  @BeforeEach
  void setUp() {
    this.sut = new NiveauEquipePersistenceAdapter(repository, mapper);
  }

  @Test
  void should_find_a_NiveauEquipe_successfully() {
    // GIVEN
    UUID id = UUID.randomUUID();
    NiveauEquipe domain = new NiveauEquipe(id, "WORLD TOUR", "WT");
    NiveauEquipeEntity entity = mapper.toEntity(domain);

    when(repository.findById(id)).thenReturn(Optional.of(entity));

    // WHEN
    Optional<NiveauEquipe> result = sut.findById(id);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertTrue(result.isPresent());
      assertEquals(domain, result.get());
    });
  }

  @Test
  void should_return_empty_Optional_when_not_finding_a_NiveauEquipe() {
    // GIVEN
    UUID id = UUID.randomUUID();
    when(repository.findById(id)).thenReturn(Optional.empty());

    // WHEN
    Optional<NiveauEquipe> result = sut.findById(id);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertFalse(result.isPresent());
    });
  }

  @Test
  void should_find_all_NiveauEquipe_successfully() {
    // GIVEN
    NiveauEquipeEntity entity1 = new NiveauEquipeEntity();
    entity1.setNiveauEquipeId(UUID.randomUUID());
    entity1.setNom("WORLD TOUR");
    entity1.setAbreviation("WT");

    NiveauEquipeEntity entity2 = new NiveauEquipeEntity();
    entity2.setNiveauEquipeId(UUID.randomUUID());
    entity2.setNom("PRO TOUR");
    entity2.setAbreviation("PRT");

    when(repository.findAll()).thenReturn(List.of(entity1, entity2));

    // WHEN
    List<NiveauEquipe> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(2, result.size());
      assertEquals("WORLD TOUR", result.get(0).nom());
      assertEquals("WT", result.get(0).abreviation());
    });
  }

  @Test
  void should_return_empty_list_when_no_NiveauEquipe_found() {
    // GIVEN
    when(repository.findAll()).thenReturn(List.of());

    // WHEN
    List<NiveauEquipe> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(0, result.size());
    });
  }

  @Test
  void should_save_new_NiveauEquipe_successfully() {
    // GIVEN
    NiveauEquipe domainToSave = NiveauEquipe.create("WORLD TOUR", "WT");
    NiveauEquipeEntity entityToReturn = mapper.toEntity(domainToSave);

    when(repository.save(any(NiveauEquipeEntity.class))).thenReturn(entityToReturn);

    // WHEN
    NiveauEquipe result = sut.save(domainToSave);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(domainToSave.nom(), result.nom());
      verify(repository, times(1)).save(any(NiveauEquipeEntity.class));
    });
  }
}
