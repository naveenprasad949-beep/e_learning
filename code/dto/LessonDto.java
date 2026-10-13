package com.learnhub.dto;

import jakarta.validation.constraints.*;

public record LessonDto(
    Long id,
    @NotNull @Positive Long courseId,
    @NotBlank @Size(max = 150) String title,
    @NotBlank @Size(max = 2000) String content) {}
