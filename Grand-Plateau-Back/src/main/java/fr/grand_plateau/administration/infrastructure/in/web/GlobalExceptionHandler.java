package fr.grand_plateau.administration.infrastructure.in.web;

import fr.grand_plateau.administration.domain.exception.ResourceAlreadyExistException;
import fr.grand_plateau.administration.domain.exception.ResourceNotFoundException;
import fr.grand_plateau.administration.infrastructure.in.web.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceAlreadyExistException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponseDTO handleResourceAlreadyExist(ResourceAlreadyExistException ex) {
    return new ErrorResponseDTO("BUSINESS_RULE_VIOLATION", ex.getMessage());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponseDTO handleResourceNotFound(ResourceNotFoundException ex) {
    return new ErrorResponseDTO("RESOURCE_NOT_FOUND", ex.getMessage());
  }
}