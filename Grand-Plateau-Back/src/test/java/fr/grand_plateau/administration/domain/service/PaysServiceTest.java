package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.out.PaysRepository;
import fr.grand_plateau.administration.domain.exception.PaysAlreadyExistException;
import fr.grand_plateau.administration.domain.exception.PaysNotFoundException;
import fr.grand_plateau.administration.domain.model.Pays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaysServiceTest {
  @Mock
  private PaysRepository paysRepository;
  @InjectMocks
  private PaysService sut;

  // --- findById() ---
  @Test
  void should_find_a_Pays_successfully() {
    // GIVEN
    UUID paysId = UUID.randomUUID();
    Pays expectedPays = new Pays(paysId, "FRANCE", "FR");
    when(paysRepository.findById(paysId)).thenReturn(Optional.of(expectedPays));

    // WHEN
    Pays result = sut.findById(paysId);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      Assertions.assertEquals(expectedPays, result);
    });
  }

  @Test
  void should_throw_PaysNotFoundException_when_no_Pays_found() {
    // GIVEN
    UUID unknownId = UUID.randomUUID();
    when(paysRepository.findById(unknownId)).thenReturn(Optional.empty());

    // THEN
    Assertions.assertThrows(PaysNotFoundException.class, () -> {
      // WHEN
      sut.findById(unknownId);
    });
  }

  // --- findAll() ---
  @Test
  void should_find_all_Pays_successfully() {
    // GIVEN
    List<Pays> expectedPays = List.of(
        new Pays(UUID.randomUUID(), "FRANCE", "FR"),
        new Pays(UUID.randomUUID(), "BELGIQUE", "BE")
    );
    when(paysRepository.findAll()).thenReturn(expectedPays);

    // WHEN
    List<Pays> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      Assertions.assertEquals(expectedPays.size(), result.size());
    });
  }

  @Test
  void should_return_empty_list_when_no_Pays_found() {
    // GIVEN
    when(paysRepository.findAll()).thenReturn(new ArrayList<>());

    // WHEN
    List<Pays> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      Assertions.assertEquals(0, result.size());
    });
  }

  // --- save() ---
  @Test
  void should_save_new_Pays_successfully() {
    // GIVEN
    String paysName = "Test";
    Pays expectedPays = new Pays(UUID.randomUUID(), "TEST", "TS");

    // WHEN
    Pays paysSaved = sut.save(paysName);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(paysSaved);
      assertEquals(expectedPays, paysSaved);
    });
  }

  @Test
  void should_throw_PaysAlreadyExistException_on_saving() {
    // GIVEN
    String paysName = "Test";
    Pays existingPays = new Pays(UUID.randomUUID(), "TEST", "TS");

    when(paysRepository.findByName(paysName)).thenReturn(Optional.of(existingPays));

    // THEN
    Assertions.assertThrows(PaysAlreadyExistException.class, () -> {
      // WHEN
      sut.save(paysName);
    });
  }

  // --- delete() ---
  @Test
  void should_delete_Pays_successfully() {
    // GIVEN
    UUID paysId = UUID.randomUUID();
    Pays paysToDelete = new Pays(paysId, "TEST", "TS");

    when(paysRepository.findById(paysId)).thenReturn(Optional.of(paysToDelete));

    // WHEN
    sut.delete(paysId);

    // THEN
    verify(paysRepository, times(1)).delete(paysId);
  }

  @Test
  void should_throw_PaysNotFoundException_when_deleting_non_existing_Pays() {
    // GIVEN
    UUID paysId = UUID.randomUUID();

    when(paysRepository.findById(paysId)).thenReturn(Optional.empty());

    // THEN
    Assertions.assertThrows(PaysNotFoundException.class, () -> {
      // WHEN
      sut.delete(paysId);
    });
  }
}