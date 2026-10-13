package com.learnhub.dto;

import jakarta.validation.constraints.*;

public final class AuthDtos {
  private AuthDtos() {}

  public record Login(@NotBlank @Email String email, @NotBlank @Size(max = 72) String password) {}

  public record Register(
      @NotBlank @Size(max = 100) String name,
      @NotBlank @Email @Size(max = 150) String email,
      @NotBlank @Size(min = 8, max = 72) String password) {}

  public record User(Long id, String name, String email, String role) {}
}
