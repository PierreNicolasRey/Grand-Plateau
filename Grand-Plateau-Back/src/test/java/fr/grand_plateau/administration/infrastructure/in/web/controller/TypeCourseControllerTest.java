package fr.grand_plateau.administration.infrastructure.in.web.controller;

import fr.grand_plateau.administration.application.ports.in.TypeCourseInputPort;
import fr.grand_plateau.administration.domain.enums.NiveauCourse;
import fr.grand_plateau.administration.domain.enums.PrestigeCourse;
import fr.grand_plateau.administration.domain.exception.TypeCourseNotFoundException;
import fr.grand_plateau.administration.domain.model.TypeCourse;
import fr.grand_plateau.administration.infrastructure.in.web.dto.TypeCourseRequest;
import fr.grand_plateau.administration.infrastructure.in.web.mapper.TypeCourseWebMapper;
import fr.grand_plateau.administration.infrastructure.in.web.mapper.TypeCourseWebMapperImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TypeCourseController.class)
@Import(TypeCourseWebMapperImpl.class)
class TypeCourseControllerTest {
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  @MockitoBean
  TypeCourseInputPort inputPort;
  @MockitoSpyBean
  TypeCourseWebMapper mapper;

  private static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";

  @Test
  void should_return_status_200_when_finding_a_TypeCourse() throws Exception {
    // GIVEN
    UUID typeCourseId = UUID.randomUUID();
    TypeCourse typeCourseDomain =
        new TypeCourse(typeCourseId, NiveauCourse.WORLD_TOUR, null, 1, PrestigeCourse.A);
    when(inputPort.findById(typeCourseId)).thenReturn(typeCourseDomain);

    // WHEN
    mockMvc.perform(get("/api/v1/type-course/{id}", typeCourseId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.typeCourseId").value(typeCourseId.toString()))
        .andExpect(jsonPath("$.badge").value("WT-1A"));

    // THEN
    verify(inputPort, times(1)).findById(typeCourseId);
    verify(mapper, times(1)).toDTO(typeCourseDomain);
  }

  @Test
  void should_return_status_404_when_not_finding_a_TypeCourse() throws Exception {
    // GIVEN
    UUID typeCourseId = UUID.randomUUID();
    when(inputPort.findById(typeCourseId)).thenThrow(new TypeCourseNotFoundException(typeCourseId));

    // WHEN
    mockMvc.perform(get("/api/v1/type-course/{id}", typeCourseId))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.code").value(RESOURCE_NOT_FOUND));

    // THEN
    verify(mapper, never()).toDTO(any(TypeCourse.class));
  }

  @Test
  void should_return_status_200_when_all_TypeCourse_found() throws Exception {
    // GIVEN
    TypeCourse typeCourseDomain1 =
        new TypeCourse(UUID.randomUUID(), NiveauCourse.WORLD_TOUR, null, 1, PrestigeCourse.A);
    TypeCourse typeCourseDomain2 =
        new TypeCourse(UUID.randomUUID(), NiveauCourse.PRO_SERIES, null, 2, null);
    when(inputPort.findAll()).thenReturn(List.of(typeCourseDomain1, typeCourseDomain2));

    // WHEN
    mockMvc.perform(get("/api/v1/type-course"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2));

    // THEN
    verify(inputPort, times(1)).findAll();
    verify(mapper, times(2)).toDTO(any(TypeCourse.class));
  }

  @Test
  void should_return_status_200_when_no_TypeCourse_found() throws Exception {
    // GIVEN
    when(inputPort.findAll()).thenReturn(List.of());

    // WHEN
    mockMvc.perform(get("/api/v1/type-course"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(0));

    // THEN
    verify(inputPort, times(1)).findAll();
  }

  @Test
  void should_return_status_201_when_new_TypeCourse_saved() throws Exception {
    // GIVEN
    UUID typeCourseId = UUID.randomUUID();
    TypeCourse typeCourseDomain =
        new TypeCourse(typeCourseId, NiveauCourse.WORLD_TOUR, null, 1, PrestigeCourse.A);
    TypeCourseRequest request =
        new TypeCourseRequest(NiveauCourse.WORLD_TOUR, null, 1, PrestigeCourse.A);

    when(inputPort.save(NiveauCourse.WORLD_TOUR, null, 1, PrestigeCourse.A))
        .thenReturn(typeCourseDomain);

    // WHEN
    mockMvc.perform(post("/api/v1/type-course")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.typeCourseId").value(typeCourseId.toString()))
        .andExpect(jsonPath("$.badge").value("WT-1A"));

    // THEN
    verify(mapper, times(1)).toDTO(typeCourseDomain);
  }
}