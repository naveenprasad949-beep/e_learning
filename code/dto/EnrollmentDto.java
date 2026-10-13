package com.learnhub.dto;

import jakarta.validation.constraints.*;

public record EnrollmentDto(
    Long id,
    @NotNull @Positive Long courseId,
    @NotNull @Min(0) @Max(100) Integer progress,
    Long ownerId) {}
