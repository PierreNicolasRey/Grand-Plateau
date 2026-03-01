package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.out.NiveauEquipeRepository;
import fr.grand_plateau.administration.domain.exception.NiveauEquipeNotFoundException;
import fr.grand_plateau.administration.domain.model.NiveauEquipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NiveauEquipeServiceTest {
  @Mock
  private NiveauEquipeRepository repository;
  @InjectMocks
  NiveauEquipeService sut;

  @Test
  void should_find_a_NiveauEquipe_by_id_successfully() {
    // GIVEN
    UUID idNiveauEquipe = UUID.randomUUID();
    NiveauEquipe expectedNiveauEquipe = new NiveauEquipe(idNiveauEquipe,"TEST", "TS");
    when(repository.findById(idNiveauEquipe)).thenReturn(Optional.of(expectedNiveauEquipe));

    // WHEN
    NiveauEquipe result = sut.findById(idNiveauEquipe);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(expectedNiveauEquipe, result);
    });
  }

  @Test
  void should_throw_NiveauEquipeNotFound_when_trying_to_find_non_existing_NiveauEquipe() {
    // GIVEN
    UUID idNiveauEquipe = UUID.randomUUID();
    when(repository.findById(idNiveauEquipe)).thenReturn(Optional.empty());

    // THEN
    Assertions.assertThrows(NiveauEquipeNotFoundException.class, () -> {
      // WHEN
      sut.findById(idNiveauEquipe);
    });
  }

  @Test
  void should_find_all_NiveauEquipe_successfully() {
    // GIVEN
    List<NiveauEquipe> expectedNiveauEquipe = List.of(
        NiveauEquipe.create("TEST", "TS"),
        NiveauEquipe.create("World Tour", "WT")
    );

    when(repository.findAll()).thenReturn(expectedNiveauEquipe);

    // WHEN
    List<NiveauEquipe> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(2, result.size());
      assertEquals("WORLD TOUR", result.get(1).nom());
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
  void should_create_new_NiveauEquipe_successfully() {
    // GIVEN
    String nom = "Continental Tour";
    String abreviation = "CT";
    NiveauEquipe expectedNiveauEquipe = new NiveauEquipe(UUID.randomUUID(), "CONTINENTAL TOUR", "CT");

    when(repository.save(any(NiveauEquipe.class))).thenReturn(expectedNiveauEquipe);

    // WHEN
    NiveauEquipe result = sut.save(nom, abreviation);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(expectedNiveauEquipe, result);
    });
  }
}
