package com.learnhub.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PaymentDto(
    Long id,
    @NotNull @Positive Long courseId,
    @NotNull @DecimalMin("0.00") @Digits(integer = 10, fraction = 2) BigDecimal amount,
    @NotBlank @Size(max = 30) String method,
    @NotBlank @Pattern(regexp = "PENDING|PAID|REFUNDED") String status,
    @NotBlank @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") String paymentDate,
    Long ownerId) {}
