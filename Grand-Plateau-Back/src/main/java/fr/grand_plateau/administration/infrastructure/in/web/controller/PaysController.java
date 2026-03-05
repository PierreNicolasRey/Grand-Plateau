package fr.grand_plateau.administration.infrastructure.in.web.controller;

import fr.grand_plateau.administration.application.ports.in.PaysInputPort;
import fr.grand_plateau.administration.domain.model.Pays;
import fr.grand_plateau.administration.infrastructure.in.web.dto.PaysDTO;
import fr.grand_plateau.administration.infrastructure.in.web.dto.PaysRequest;
import fr.grand_plateau.administration.infrastructure.in.web.mapper.PaysWebMapper;
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
@RequestMapping("/api/v1/pays")
public class PaysController {
  private final PaysInputPort paysInputPort;
  private final PaysWebMapper paysWebMapper;

  public PaysController(PaysInputPort paysInputPort, PaysWebMapper paysWebMapper) {
    this.paysInputPort = paysInputPort;
    this.paysWebMapper = paysWebMapper;
  }

  @GetMapping("/{id}")
  public ResponseEntity<PaysDTO> findById(@PathVariable("id") UUID id) {
    Pays paysDomain = this.paysInputPort.findById(id);

    return new ResponseEntity<>(this.paysWebMapper.toDTO(paysDomain), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<PaysDTO>> findAll() {
    List<PaysDTO> paysDTOList = this.paysInputPort.findAll().stream()
        .map(this.paysWebMapper::toDTO).toList();

    return new ResponseEntity<>(paysDTOList, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<PaysDTO> save(@RequestBody PaysRequest paysRequest) {
    Pays paysSaved = this.paysInputPort.save(paysRequest.nom(), paysRequest.codeISO());

    return new ResponseEntity<>(this.paysWebMapper.toDTO(paysSaved), HttpStatus.CREATED);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") UUID id) {
    this.paysInputPort.delete(id);
  }
}
