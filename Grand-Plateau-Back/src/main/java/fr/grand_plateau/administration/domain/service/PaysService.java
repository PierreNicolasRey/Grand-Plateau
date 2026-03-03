package fr.grand_plateau.administration.domain.service;

import fr.grand_plateau.administration.application.ports.in.PaysInputPort;
import fr.grand_plateau.administration.application.ports.out.PaysRepository;
import fr.grand_plateau.administration.domain.exception.PaysAlreadyExistException;
import fr.grand_plateau.administration.domain.exception.PaysNotFoundException;
import fr.grand_plateau.administration.domain.model.Pays;
import fr.grand_plateau.administration.infrastructure.in.web.dto.PaysRequest;

import java.util.List;
import java.util.UUID;

public class PaysService implements PaysInputPort {
  private final PaysRepository paysRepository;

  public PaysService(PaysRepository paysRepository) {
    this.paysRepository = paysRepository;
  }

  @Override
  public Pays findById(UUID id) {
    return this.paysRepository.findById(id).orElseThrow(() -> new PaysNotFoundException(id));
  }

  @Override
  public List<Pays> findAll() {
    return this.paysRepository.findAll();
  }

  @Override
  public Pays save(String nom, String codeISO) {
    if (this.paysRepository.findByNom(nom).isPresent()) {
      throw new PaysAlreadyExistException(nom);
    }

    Pays paysToSave = Pays.create(nom, codeISO);

    return this.paysRepository.save(paysToSave);
  }

  @Override
  public void delete(UUID id) {
    if (this.paysRepository.findById(id).isEmpty()) {
      throw new PaysNotFoundException(id);
    }

    this.paysRepository.delete(id);
  }
}
