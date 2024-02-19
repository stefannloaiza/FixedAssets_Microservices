package com.perficient.fixedassets.newsmicroservice.domain.models.dto;

import java.time.LocalDate;

public record NewsDTO(
        Long id,
        Long assetId,
        LocalDate registrationDate,
        String title,
        String description
) {
}