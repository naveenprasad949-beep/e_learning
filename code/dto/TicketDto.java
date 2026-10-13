package com.learnhub.dto;

import jakarta.validation.constraints.*;

public record TicketDto(
    Long id,
    @NotBlank @Size(max = 150) String subject,
    @NotBlank @Size(max = 2000) String message,
    @NotBlank @Pattern(regexp = "OPEN|IN_PROGRESS|CLOSED") String status,
    @Size(max = 2000) String reply,
    Long ownerId) {}
