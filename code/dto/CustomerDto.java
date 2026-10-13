package com.learnhub.dto;

import jakarta.validation.constraints.*;

public record CustomerDto(
    Long id,
    @NotBlank @Size(max = 100) String name,
    @NotBlank @Email @Size(max = 150) String email,
    @Size(min = 8, max = 72)
        @com.fasterxml.jackson.annotation.JsonProperty(
            access = com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY)
        String password) {}
