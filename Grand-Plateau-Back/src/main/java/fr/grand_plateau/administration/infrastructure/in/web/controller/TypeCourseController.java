package fr.grand_plateau.administration.infrastructure.in.web.controller;

import fr.grand_plateau.administration.application.ports.in.TypeCourseInputPort;
import fr.grand_plateau.administration.domain.model.TypeCourse;
import fr.grand_plateau.administration.infrastructure.in.web.dto.TypeCourseDTO;
import fr.grand_plateau.administration.infrastructure.in.web.dto.TypeCourseRequest;
import fr.grand_plateau.administration.infrastructure.in.web.mapper.TypeCourseWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v1/type-course")
public class TypeCourseController {
  private final TypeCourseInputPort typeCourseInputPort;
  private final TypeCourseWebMapper typeCourseWebMapper;

  public TypeCourseController(TypeCourseInputPort typeCourseInputPort,
                              TypeCourseWebMapper typeCourseWebMapper) {
    this.typeCourseInputPort = typeCourseInputPort;
    this.typeCourseWebMapper = typeCourseWebMapper;
  }

  @GetMapping("/{id}")
  public ResponseEntity<TypeCourseDTO> findById(@PathVariable("id") UUID typeCourseId) {
    TypeCourse typeCourseDomain = this.typeCourseInputPort.findById(typeCourseId);

    return new ResponseEntity<>(this.typeCourseWebMapper.toDTO(typeCourseDomain), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<TypeCourseDTO>> findAll() {
    List<TypeCourseDTO> typeCourseDTOList =
        this.typeCourseInputPort.findAll().stream().map(this.typeCourseWebMapper::toDTO).toList();

    return new ResponseEntity<>(typeCourseDTOList, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<TypeCourseDTO> save(@RequestBody @Valid TypeCourseRequest request) {
    TypeCourse typeCourseSaved = this.typeCourseInputPort.save(request.niveauCourse(), request.zoneChampionnat(),
        request.duree(), request.prestigeCourse());

    return new ResponseEntity<>(this.typeCourseWebMapper.toDTO(typeCourseSaved), HttpStatus.CREATED);
  }
}
