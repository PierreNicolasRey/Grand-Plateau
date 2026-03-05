package fr.grand_plateau.administration.infrastructure.in.web.controller;

import fr.grand_plateau.administration.application.ports.in.NiveauEquipeInputPort;
import fr.grand_plateau.administration.infrastructure.in.web.dto.NiveauEquipeDTO;
import fr.grand_plateau.administration.infrastructure.in.web.dto.NiveauEquipeRequest;
import fr.grand_plateau.administration.infrastructure.in.web.mapper.NiveauEquipeWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/niveau-equipe")
public class NiveauEquipeController {
  private final NiveauEquipeInputPort niveauEquipeInputPort;
  private final NiveauEquipeWebMapper niveauEquipeWebMapper;

  public NiveauEquipeController(NiveauEquipeInputPort niveauEquipeInputPort,
                                NiveauEquipeWebMapper niveauEquipeWebMapper) {
    this.niveauEquipeInputPort = niveauEquipeInputPort;
    this.niveauEquipeWebMapper = niveauEquipeWebMapper;
  }

  @GetMapping("/{id}")
  public ResponseEntity<NiveauEquipeDTO> findById(@PathVariable("id") UUID niveauEquipeId) {
    return null;
  }

  @GetMapping
  public ResponseEntity<List<NiveauEquipeDTO>> findAll() {
    return null;
  }

  @PostMapping
  public ResponseEntity<NiveauEquipeDTO> save(@RequestBody @Valid NiveauEquipeRequest request) {
    return null;
  }
}
