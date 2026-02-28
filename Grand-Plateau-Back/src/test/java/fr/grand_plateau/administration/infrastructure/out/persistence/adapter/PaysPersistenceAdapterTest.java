package fr.grand_plateau.administration.infrastructure.out.persistence.adapter;

import fr.grand_plateau.administration.domain.model.Pays;
import fr.grand_plateau.administration.infrastructure.out.persistence.entity.PaysEntity;
import fr.grand_plateau.administration.infrastructure.out.persistence.mapper.PaysPersistenceMapper;
import fr.grand_plateau.administration.infrastructure.out.persistence.repository.SpringDataPaysRepository;
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
class PaysPersistenceAdapterTest {
  @Mock
  private SpringDataPaysRepository paysRepository;

  private final PaysPersistenceMapper mapper = Mappers.getMapper(PaysPersistenceMapper.class);

  PaysPersistenceAdapter sut;

  @BeforeEach
  void setUp() {
    this.sut = new PaysPersistenceAdapter(paysRepository, mapper);
  }

  @Test
  void should_find_a_PaysEntity_and_map_it_correctly() {
    // GIVEN
    UUID paysId = UUID.randomUUID();

    Pays expectedPays = new Pays(paysId, "FRANCE", "FR");

    PaysEntity entity = mapper.toEntity(expectedPays);

    when(paysRepository.findById(paysId)).thenReturn(Optional.of(entity));

    // WHEN
    Optional<Pays> result = sut.findById(paysId);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertTrue(result.isPresent());
      assertEquals(expectedPays, result.get());
    });
  }

  @Test
  void should_return_empty_optional_when_not_finding_a_pays() {
    // GIVEN
    UUID paysId = UUID.randomUUID();
    when(paysRepository.findById(paysId)).thenReturn(Optional.empty());

    // WHEN
    Optional<Pays> result = sut.findById(paysId);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertFalse(result.isPresent());
    });
  }

  @Test
  void should_find_a_PaysEntity_by_its_name_and_map_it_correctly() {
    // GIVEN
    UUID paysId = UUID.randomUUID();
    String nomPays = "FRANCE";
    Pays expectedPays = new Pays(paysId, nomPays, "FR");

    PaysEntity entity = mapper.toEntity(expectedPays);

    when(paysRepository.findByNom(nomPays)).thenReturn(Optional.of(entity));

    // WHEN
    Optional<Pays> result = sut.findByNom(nomPays);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertTrue(result.isPresent());
      assertEquals(expectedPays, result.get());
    });
  }

  @Test
  void should_return_empty_optional_when_not_finding_by_its_name_a_pays() {
    // GIVEN
    String nomPays = "FRANCE";
    when(paysRepository.findByNom(nomPays)).thenReturn(Optional.empty());

    // WHEN
    Optional<Pays> result = sut.findByNom(nomPays);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertFalse(result.isPresent());
    });
  }

  @Test
  void should_find_all_pays_successfully() {
    // GIVEN
    PaysEntity pays1 = new PaysEntity();
    pays1.setPaysId(UUID.randomUUID());
    pays1.setNom("FRANCE");
    pays1.setCodeIso("FR");

    PaysEntity pays2 = new PaysEntity();
    pays2.setPaysId(UUID.randomUUID());
    pays2.setNom("BELGIQUE");
    pays2.setCodeIso("BE");

    List<PaysEntity> paysEntities = List.of(pays1, pays2);
    when(paysRepository.findAll()).thenReturn(paysEntities);

    // WHEN
    List<Pays> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(2, result.size());
      assertEquals("FR", result.get(0).codeIso());
      assertEquals("BE", result.get(1).codeIso());
    });
  }

  @Test
  void should_return_empty_list_when_not_finding_all_pays() {
    // GIVEN
    when(paysRepository.findAll()).thenReturn(List.of());

    // WHEN
    List<Pays> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(0, result.size());
    });
  }

  @Test
  void save() {
    // GIVEN
    Pays paysToSave = Pays.create("FRANCE", "FR");
    PaysEntity expectedEntity = mapper.toEntity(paysToSave);
    Pays expectedPays = mapper.toDomain(expectedEntity);

    when(paysRepository.save(any(PaysEntity.class))).thenReturn(expectedEntity);

    // WHEN
    Pays result = sut.save(paysToSave);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(expectedPays, result);
    });
  }

  @Test
  void delete() {
    // GIVEN
    UUID paysId = UUID.randomUUID();

    // WHEN
    sut.delete(paysId);

    // THEN
    verify(paysRepository, times(1)).deleteById(paysId);
  }
}