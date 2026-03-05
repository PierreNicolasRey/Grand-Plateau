package fr.grand_plateau.administration.infrastructure.in.web.controller;

import fr.grand_plateau.administration.application.ports.in.CategorieCourseInputPort;
import fr.grand_plateau.administration.infrastructure.in.web.dto.CategorieCourseDTO;
import fr.grand_plateau.administration.infrastructure.in.web.dto.CategorieCourseRequest;
import fr.grand_plateau.administration.infrastructure.in.web.mapper.CategorieCourseWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categorie-course")
public class CategorieCourseController {
  private final CategorieCourseInputPort categorieCourseInputPort;
  private final CategorieCourseWebMapper categorieCourseWebMapper;

  public CategorieCourseController(CategorieCourseInputPort categorieCourseInputPort,
                                   CategorieCourseWebMapper categorieCourseWebMapper) {
    this.categorieCourseInputPort = categorieCourseInputPort;
    this.categorieCourseWebMapper = categorieCourseWebMapper;
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategorieCourseDTO> findById(@PathVariable("id")UUID categorieCourseId) {
    return null;
  }

  @GetMapping
  public ResponseEntity<List<CategorieCourseDTO>> findAll() {
    return null;
  }

  @PostMapping
  public ResponseEntity<CategorieCourseDTO> save(@RequestBody @Valid CategorieCourseRequest request) {
    return null;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id")UUID categorieCourseId) {

  }
}
