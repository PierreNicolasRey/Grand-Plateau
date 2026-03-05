package fr.grand_plateau.administration.infrastructure.in.web.controller;

import fr.grand_plateau.administration.application.ports.in.NiveauEquipeInputPort;
import fr.grand_plateau.administration.domain.exception.NiveauEquipeNotFoundException;
import fr.grand_plateau.administration.domain.model.NiveauEquipe;
import fr.grand_plateau.administration.infrastructure.in.web.dto.NiveauEquipeRequest;
import fr.grand_plateau.administration.infrastructure.in.web.mapper.NiveauEquipeWebMapper;
import fr.grand_plateau.administration.infrastructure.in.web.mapper.NiveauEquipeWebMapperImpl;
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

@WebMvcTest(NiveauEquipeController.class)
@Import(NiveauEquipeWebMapperImpl.class)
class NiveauEquipeControllerTest {
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  @MockitoBean
  NiveauEquipeInputPort inputPort;
  @MockitoSpyBean
  NiveauEquipeWebMapper mapper;

  private static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
  private static final String NOM_NIVEAU_EQUIPE = "WORLD TOUR";
  private static final String ABREVIATION_NIVEAU_EQUIPE = "WT";
  @Test
  void should_return_status_200_when_finding_a_NiveauEquipe() throws Exception {
    // GIVEN
    UUID niveauEquipeId = UUID.randomUUID();
    NiveauEquipe niveauEquipeDomain = new NiveauEquipe(niveauEquipeId, NOM_NIVEAU_EQUIPE, ABREVIATION_NIVEAU_EQUIPE);
    when(inputPort.findById(niveauEquipeId)).thenReturn(niveauEquipeDomain);

    // WHEN
    mockMvc.perform(get("/api/v1/niveau-equipe/{id}", niveauEquipeId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.niveauEquipeId").value(niveauEquipeId.toString()));

    // THEN
    verify(inputPort, times(1)).findById(niveauEquipeId);
    verify(mapper, times(1)).toDTO(niveauEquipeDomain);
  }

  @Test
  void should_return_status_404_when_not_finding_a_NiveauEquipe() throws Exception {
    // GIVEN
    UUID niveauEquipeId = UUID.randomUUID();
    when(inputPort.findById(niveauEquipeId)).thenThrow(new NiveauEquipeNotFoundException(niveauEquipeId));

    // WHEN
    mockMvc.perform(get("/api/v1/niveau-equipe/{id}", niveauEquipeId))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.code").value(RESOURCE_NOT_FOUND));

    // THEN
    verify(mapper, never()).toDTO(any(NiveauEquipe.class));
  }

  @Test
  void should_return_status_200_when_all_NiveauEquipe_found() throws Exception {
    // GIVEN
    NiveauEquipe niveauEquipeDomain1 = new NiveauEquipe(UUID.randomUUID(), NOM_NIVEAU_EQUIPE, ABREVIATION_NIVEAU_EQUIPE);
    NiveauEquipe niveauEquipeDomain2 = new NiveauEquipe(UUID.randomUUID(), "PRO TOUR", "PRT");
    when(inputPort.findAll()).thenReturn(List.of(niveauEquipeDomain1, niveauEquipeDomain2));

    // WHEN
    mockMvc.perform(get("/api/v1/niveau-equipe"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2));

    // THEN
    verify(inputPort, times(1)).findAll();
    verify(mapper, times(2)).toDTO(any(NiveauEquipe.class));
  }

  @Test
  void should_return_status_200_when_no_NiveauEquipe_found() throws Exception {
    // GIVEN
    when(inputPort.findAll()).thenReturn(List.of());

    // WHEN
    mockMvc.perform(get("/api/v1/niveau-equipe"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(0));

    // THEN
    verify(inputPort, times(1)).findAll();
  }

  @Test
  void should_return_status_201_when_new_NiveauEquipe_saved() throws Exception {
    // GIVEN
    UUID niveauEquipeId = UUID.randomUUID();
    String nom = NOM_NIVEAU_EQUIPE;
    String codeISO = ABREVIATION_NIVEAU_EQUIPE;
    NiveauEquipe niveauEquipeDomain = new NiveauEquipe(niveauEquipeId, nom, codeISO);
    NiveauEquipeRequest request = new NiveauEquipeRequest(nom, codeISO);

    when(inputPort.save(nom, codeISO)).thenReturn(niveauEquipeDomain);

    // WHEN
    mockMvc.perform(post("/api/v1/niveau-equipe")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.niveauEquipeId").value(niveauEquipeId.toString()));

    // THEN
    verify(mapper, times(1)).toDTO(niveauEquipeDomain);
  }
}