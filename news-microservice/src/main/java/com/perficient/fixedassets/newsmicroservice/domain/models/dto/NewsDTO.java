package com.perficient.fixedassets.newsmicroservice.domain.models.dto;

import com.perficient.fixedassets.newsmicroservice.domain.models.enums.Status;

import java.time.LocalDate;

public record NewsDTO(
        Long id,
        Long assetId,
        String userNotifier,
        LocalDate registrationDate,
        String title,
        String description,
        Status status,
        String userAssigned
) {
}