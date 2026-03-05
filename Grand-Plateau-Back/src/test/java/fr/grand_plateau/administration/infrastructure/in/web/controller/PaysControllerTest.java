package fr.grand_plateau.administration.infrastructure.in.web.controller;

import fr.grand_plateau.administration.application.ports.in.PaysInputPort;
import fr.grand_plateau.administration.domain.exception.PaysAlreadyExistException;
import fr.grand_plateau.administration.domain.exception.PaysNotFoundException;
import fr.grand_plateau.administration.domain.model.Pays;
import fr.grand_plateau.administration.infrastructure.in.web.controller.PaysController;
import fr.grand_plateau.administration.infrastructure.in.web.dto.PaysRequest;
import fr.grand_plateau.administration.infrastructure.in.web.mapper.PaysWebMapper;
import fr.grand_plateau.administration.infrastructure.in.web.mapper.PaysWebMapperImpl;
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

@WebMvcTest(PaysController.class)
@Import(PaysWebMapperImpl.class)
class PaysControllerTest {
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  @MockitoBean
  PaysInputPort inputPort;
  @MockitoSpyBean
  PaysWebMapper mapper;

  private static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
  private static final String BUSINESS_RULE_VIOLATION = "BUSINESS_RULE_VIOLATION";

  @Test
  void should_return_status_200_when_finding_a_Pays() throws Exception {
    // GIVEN
    UUID paysId = UUID.randomUUID();
    Pays paysDomain = new Pays(paysId, "TEST", "TS");
    when(inputPort.findById(paysId)).thenReturn(paysDomain);

    // WHEN
    mockMvc.perform(get("/api/v1/pays/{id}", paysId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.paysId").value(paysId.toString()));

    // THEN
    verify(inputPort, times(1)).findById(paysId);
    verify(mapper, times(1)).toDTO(paysDomain);
  }

  @Test
  void should_return_status_404_when_not_finding_a_Pays() throws Exception {
    // GIVEN
    UUID paysId = UUID.randomUUID();
    when(inputPort.findById(paysId)).thenThrow(new PaysNotFoundException(paysId));

    // WHEN
    mockMvc.perform(get("/api/v1/pays/{id}", paysId))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.code").value(RESOURCE_NOT_FOUND));

    // THEN
    verify(mapper, never()).toDTO(any(Pays.class));
  }

  @Test
  void should_return_status_200_when_all_Pays_found() throws Exception {
    // GIVEN
    Pays paysDomain1 = new Pays(UUID.randomUUID(), "TEST", "TS");
    Pays paysDomain2 = new Pays(UUID.randomUUID(), "FRANCE", "FR");
    when(inputPort.findAll()).thenReturn(List.of(paysDomain1, paysDomain2));

    // WHEN
    mockMvc.perform(get("/api/v1/pays"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2));

    // THEN
    verify(inputPort, times(1)).findAll();
    verify(mapper, times(2)).toDTO(any(Pays.class));
  }

  @Test
  void should_return_status_200_when_no_Pays_found() throws Exception {
    // GIVEN
    when(inputPort.findAll()).thenReturn(List.of());

    // WHEN
    mockMvc.perform(get("/api/v1/pays"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(0));

    // THEN
    verify(inputPort, times(1)).findAll();
  }

  @Test
  void should_return_status_201_when_new_Pays_saved() throws Exception {
    // GIVEN
    UUID paysId = UUID.randomUUID();
    String nom = "TEST";
    String codeISO = "TS";
    Pays paysDomain = new Pays(paysId, nom, codeISO);
    PaysRequest request = new PaysRequest(nom, codeISO);

    when(inputPort.save(nom, codeISO)).thenReturn(paysDomain);

    // WHEN
    mockMvc.perform(post("/api/v1/pays")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.paysId").value(paysId.toString()));

    // THEN
    verify(mapper, times(1)).toDTO(paysDomain);
  }

  @Test
  void should_return_status_409_when_saving_already_existing_Pays() throws Exception {
    // GIVEN
    String nom = "TEST";
    String codeISO = "TS";
    PaysRequest request = new PaysRequest(nom, codeISO);

    when(inputPort.save(nom, codeISO)).thenThrow(new PaysAlreadyExistException(nom));

    // WHEN
    mockMvc.perform(post("/api/v1/pays")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.code").value(BUSINESS_RULE_VIOLATION));

    // THEN
    verifyNoInteractions(mapper);
  }

  @Test
  void should_return_status_204_when_deleting_Pays() throws Exception {
    // GIVEN
    UUID paysId = UUID.randomUUID();

    // WHEN
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/pays/{id}", paysId))
        .andExpect(status().isNoContent());

    // THEN
    verify(inputPort, times(1)).delete(paysId);
  }

  @Test
  void should_return_status_404_when_deleting_non_existing_pays() throws Exception {
    // GIVEN
    UUID paysId = UUID.randomUUID();
    doThrow(new PaysNotFoundException(paysId)).when(inputPort).delete(paysId);

    // WHEN / THEN
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/pays/{id}", paysId))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.code").value(RESOURCE_NOT_FOUND));
  }
}