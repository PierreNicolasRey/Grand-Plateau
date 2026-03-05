package fr.grand_plateau.administration.infrastructure.in.web.controller;

import fr.grand_plateau.administration.application.ports.in.CategorieCourseInputPort;
import fr.grand_plateau.administration.domain.exception.CategorieCourseAlreadyExistException;
import fr.grand_plateau.administration.domain.exception.CategorieCourseNotFoundException;
import fr.grand_plateau.administration.domain.model.CategorieCourse;
import fr.grand_plateau.administration.infrastructure.in.web.dto.CategorieCourseRequest;
import fr.grand_plateau.administration.infrastructure.in.web.mapper.CategorieCourseWebMapper;
import fr.grand_plateau.administration.infrastructure.in.web.mapper.CategorieCourseWebMapperImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategorieCourseController.class)
@Import(CategorieCourseWebMapperImpl.class)
class CategorieCourseControllerTest {
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  @MockitoBean
  CategorieCourseInputPort inputPort;

  @MockitoSpyBean
  CategorieCourseWebMapper mapper;

  private static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
  private static final String BUSINESS_RULE_VIOLATION = "BUSINESS_RULE_VIOLATION";

  @Test
  void should_return_status_200_when_finding_a_CategorieCourse() throws Exception {
    // GIVEN
    UUID catCourseId = UUID.randomUUID();
    CategorieCourse catCourseDomain = new CategorieCourse(catCourseId, "TEST", "TS");
    when(inputPort.findById(catCourseId)).thenReturn(catCourseDomain);

    // WHEN
    mockMvc.perform(get("/api/v1/categorie-course/{id}", catCourseId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.categorieCourseId").value(catCourseId.toString()));

    // THEN
    verify(inputPort, times(1)).findById(catCourseId);
    verify(mapper, times(1)).toDTO(catCourseDomain);
  }

  @Test
  void should_return_status_404_when_not_finding_a_CategorieCourse() throws Exception {
    // GIVEN
    UUID catCourseId = UUID.randomUUID();
    when(inputPort.findById(catCourseId)).thenThrow(new CategorieCourseNotFoundException(catCourseId));

    // WHEN
    mockMvc.perform(get("/api/v1/categorie-course/{id}", catCourseId))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.code").value(RESOURCE_NOT_FOUND));

    // THEN
    verify(mapper, never()).toDTO(any(CategorieCourse.class));
  }

  @Test
  void should_return_status_200_when_all_CategorieCourse_found() throws Exception {
    // GIVEN
    CategorieCourse catCourseDomain1 = new CategorieCourse(UUID.randomUUID(), "TEST", "TS");
    CategorieCourse catCourseDomain2 = new CategorieCourse(UUID.randomUUID(), "Catégorie 1", "C1");
    when(inputPort.findAll()).thenReturn(List.of(catCourseDomain1, catCourseDomain2));

    // WHEN
    mockMvc.perform(get("/api/v1/categorie-course"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2));

    // THEN
    verify(inputPort, times(1)).findAll();
    verify(mapper, times(2)).toDTO(any(CategorieCourse.class));
  }

  @Test
  void should_return_status_200_when_no_CategorieCourse_found() throws Exception {
    // GIVEN
    when(inputPort.findAll()).thenReturn(List.of());

    // WHEN
    mockMvc.perform(get("/api/v1/categorie-course"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(0));

    // THEN
    verify(inputPort, times(1)).findAll();
  }

  @Test
  void should_return_status_201_when_new_CategorieCourse_saved() throws Exception {
    // GIVEN
    UUID catCourseId = UUID.randomUUID();
    String nom = "Catégorie 2";
    String abreviation = "C2";
    CategorieCourse catCourseDomain = new CategorieCourse(catCourseId, nom, abreviation);

    CategorieCourseRequest request = new CategorieCourseRequest(nom);

    when(inputPort.save(nom)).thenReturn(catCourseDomain);

    // WHEN
    mockMvc.perform(post("/api/v1/categorie-course")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.categorieCourseId").value(catCourseId.toString()))
        .andExpect(jsonPath("$.abreviation").value(abreviation));

    // THEN
    verify(mapper, times(1)).toDTO(catCourseDomain);
  }

  @Test
  void should_return_status_409_when_saving_already_existing_CategorieCourse() throws Exception {
    // GIVEN
    String nom = "TEST";
    CategorieCourseRequest request = new CategorieCourseRequest(nom);

    when(inputPort.save(nom)).thenThrow(new CategorieCourseAlreadyExistException(nom));

    // WHEN
    mockMvc.perform(post("/api/v1/categorie-course")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.code").value(BUSINESS_RULE_VIOLATION));

    // THEN
    verifyNoInteractions(mapper);
  }

  @Test
  void should_return_status_204_when_deleting_CategorieCourse() throws Exception {
    // GIVEN
    UUID catCourseId = UUID.randomUUID();

    // WHEN
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/categorie-course/{id}", catCourseId))
        .andExpect(status().isNoContent());

    // THEN
    verify(inputPort, times(1)).delete(catCourseId);
  }

  @Test
  void should_return_status_404_when_deleting_non_existing_CategorieCourse() throws Exception {
    // GIVEN
    UUID catCourseId = UUID.randomUUID();
    doThrow(new CategorieCourseNotFoundException(catCourseId)).when(inputPort).delete(catCourseId);

    // WHEN / THEN
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/categorie-course/{id}", catCourseId))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.code").value(RESOURCE_NOT_FOUND));
  }
}