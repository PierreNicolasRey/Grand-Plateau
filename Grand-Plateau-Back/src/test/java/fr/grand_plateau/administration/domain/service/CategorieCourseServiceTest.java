package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.out.CategorieCourseRepository;
import fr.grand_plateau.administration.domain.exception.CategorieCourseAlreadyExistException;
import fr.grand_plateau.administration.domain.exception.CategorieCourseNotFoundException;
import fr.grand_plateau.administration.domain.model.CategorieCourse;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategorieCourseServiceTest {
  @Mock
  private CategorieCourseRepository categorieCourseRepository;
  @InjectMocks
  private CategorieCourseService sut;

  @Test
  void should_find_a_CategorieCourse_successfully() {
    // GIVEN
    UUID id = UUID.randomUUID();
    CategorieCourse expectedCategorieCourse = new CategorieCourse(id, "Catégorie 1", "C1");
    when(categorieCourseRepository.findById(id)).thenReturn(Optional.of(expectedCategorieCourse));

    // WHEN
    CategorieCourse result = sut.findById(id);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(expectedCategorieCourse, result);
    });
  }

  @Test
  void should_throw_CategorieCourseNotFoundException_when_no_CategorieCourse_found() {
    // GIVEN
    UUID id = UUID.randomUUID();
    when(categorieCourseRepository.findById(id)).thenReturn(Optional.empty());

    // THEN
    Assertions.assertThrows(CategorieCourseNotFoundException.class, () -> {
      // WHEN
      sut.findById(id);
    });
  }

  @Test
  void should_find_all_CategorieCourse_successfully() {
    // GIVEN
    List<CategorieCourse> expectedCategories = List.of(
        new CategorieCourse(UUID.randomUUID(), "Catégorie 1", "C1"),
        new CategorieCourse(UUID.randomUUID(), "Catégorie 2", "C2")
    );
    when(categorieCourseRepository.findAll()).thenReturn(expectedCategories);

    // WHEN
    List<CategorieCourse> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      Assertions.assertEquals(expectedCategories.size(), result.size());
    });
  }

  @Test
  void should_return_empty_list_when_no_CategorieCourse_found() {
    // GIVEN
    when(categorieCourseRepository.findAll()).thenReturn(new ArrayList<>());

    // WHEN
    List<CategorieCourse> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      Assertions.assertEquals(0, result.size());
    });
  }

  // --- save() ---
  @Test
  void should_save_new_CategorieCourse_successfully() {
    // GIVEN
    String categorieName = "Catégorie 1";
    CategorieCourse expectedCategorie = new CategorieCourse(UUID.randomUUID(), categorieName, "C1");

    when(categorieCourseRepository.save(any(CategorieCourse.class))).thenReturn(expectedCategorie);

    // WHEN
    CategorieCourse categorieCourseSaved = sut.save(categorieName);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(categorieCourseSaved);
      assertEquals(expectedCategorie, categorieCourseSaved);
    });
  }

  @Test
  void should_throw_CategorieCourseAlreadyExistException_on_saving() {
    // GIVEN
    String categorieName = "Catégorie 1";
    CategorieCourse existingCategorie = new CategorieCourse(UUID.randomUUID(), categorieName, "C1");

    when(categorieCourseRepository.findByNom(categorieName)).thenReturn(Optional.of(existingCategorie));

    // THEN
    Assertions.assertThrows(CategorieCourseAlreadyExistException.class, () -> {
      // WHEN
      sut.save(categorieName);
    });
  }

  // --- delete() ---
  @Test
  void should_delete_CategorieCourse_successfully() {
    // GIVEN
    UUID categorieId = UUID.randomUUID();
    String categorieName = "Catégorie 1";
    CategorieCourse categorieToDelete = new CategorieCourse(categorieId, categorieName, "C1");

    when(categorieCourseRepository.findById(categorieId)).thenReturn(Optional.of(categorieToDelete));

    // WHEN
    sut.delete(categorieId);

    // THEN
    verify(categorieCourseRepository, times(1)).delete(categorieId);
  }

  @Test
  void should_throw_CategorieCourseNotFoundException_when_deleting_non_existing_CategorieCourse() {
    // GIVEN
    UUID categorieId = UUID.randomUUID();

    when(categorieCourseRepository.findById(categorieId)).thenReturn(Optional.empty());

    // THEN
    Assertions.assertThrows(CategorieCourseNotFoundException.class, () -> {
      // WHEN
      sut.delete(categorieId);
    });
  }
}
