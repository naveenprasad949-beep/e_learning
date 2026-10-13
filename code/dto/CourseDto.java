package com.learnhub.dto;

import jakarta.validation.constraints.*;

public record CourseDto(
    Long id,
    @NotBlank @Size(max = 150) String title,
    @NotBlank @Size(max = 100) String category,
    @NotBlank @Size(max = 2000) String description) {}
