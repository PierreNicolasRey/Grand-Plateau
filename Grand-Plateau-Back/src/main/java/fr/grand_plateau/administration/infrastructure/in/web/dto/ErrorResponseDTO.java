package fr.grand_plateau.administration.infrastructure.in.web.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
    String code,
    String message,
    LocalDateTime timestamp) {
  public ErrorResponseDTO(String code, String message) {
    this(code, message, LocalDateTime.now());
  }
}