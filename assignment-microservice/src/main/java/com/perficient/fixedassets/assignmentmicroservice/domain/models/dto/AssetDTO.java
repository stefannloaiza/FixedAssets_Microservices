package com.perficient.fixedassets.assignmentmicroservice.domain.models.dto;

import com.perficient.fixedassets.assignmentmicroservice.domain.models.enums.Status;

import java.time.LocalDate;

public record AssetDTO(
        Long id,
        String name,
        String description,
        String code,
        Status status,
        LocalDate acquisitionDate,
        Double acquisitionCost,
        Status assignmentStatus
) {
}
