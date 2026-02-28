package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.out.TypeCourseRepository;
import fr.grand_plateau.administration.domain.enums.NiveauCourse;
import fr.grand_plateau.administration.domain.enums.PrestigeCourse;
import fr.grand_plateau.administration.domain.enums.ZoneChampionnat;
import fr.grand_plateau.administration.domain.exception.TypeCourseNotFoundException;
import fr.grand_plateau.administration.domain.model.TypeCourse;
import fr.grand_plateau.administration.domain.model.TypeCourseRequest;
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
class TypeCourseServiceTest {
  @Mock
  private TypeCourseRepository typeCourseRepository;
  @InjectMocks
  private TypeCourseService sut;

  @Test
  void should_find_a_TypeCourse_successfully() {
    // GIVEN
    UUID typeCourseId = UUID.randomUUID();
    TypeCourse expectedTypeCourse =
        new TypeCourse(typeCourseId, NiveauCourse.WORLD_TOUR, null, 1, PrestigeCourse.A);

    when(typeCourseRepository.findById(typeCourseId)).thenReturn(Optional.of(expectedTypeCourse));

    // WHEN
    TypeCourse result = sut.findById(typeCourseId);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(expectedTypeCourse, result);
    });
  }

  @Test
  void should_throw_TypeCourseNotFound_when_trying_to_find_non_existing_TypeCourse() {
    // GIVEN
    UUID typeCourseId = UUID.randomUUID();

    when(typeCourseRepository.findById(typeCourseId)).thenReturn(Optional.empty());

    // THEN
    Assertions.assertThrows(TypeCourseNotFoundException.class, () -> {
      // WHEN
      sut.findById(typeCourseId);
    });
  }

  @Test
  void should_find_all_TypeCourse_successfully() {
    // GIVEN
    List<TypeCourse> typeCourses = List.of(
        new TypeCourse(UUID.randomUUID(), NiveauCourse.WORLD_TOUR, null, 1, PrestigeCourse.A),
        new TypeCourse(UUID.randomUUID(), NiveauCourse.CHAMPIONNAT, ZoneChampionnat.NATIONAL, null, PrestigeCourse.C)
    );

    when(typeCourseRepository.findAll()).thenReturn(typeCourses);

    // WHEN
    List<TypeCourse> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(2, result.size());
      assertEquals(typeCourses, result);
    });
  }

  @Test
  void should_return_empty_list_when_no_TypeCourses_found() {
    // GIVEN
    when(typeCourseRepository.findAll()).thenReturn(List.of());

    // WHEN
    List<TypeCourse> result = sut.findAll();

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(0, result.size());
    });
  }

  @Test
  void should_create_TypeCourse_successfully() {
    // GIVEN
    TypeCourseRequest typeCourseRequest =
        new TypeCourseRequest(NiveauCourse.WORLD_TOUR, null, 1, PrestigeCourse.A);
    TypeCourse expectedTypeCourse =
        new TypeCourse(UUID.randomUUID(), NiveauCourse.WORLD_TOUR, null, 1, PrestigeCourse.A);

    when(typeCourseRepository.save(any(TypeCourse.class))).thenReturn(expectedTypeCourse);

    // WHEN
    TypeCourse result = sut.save(typeCourseRequest);

    // THEN
    Assertions.assertAll(() -> {
      assertNotNull(result);
      assertEquals(expectedTypeCourse, result);
    });
  }
}
