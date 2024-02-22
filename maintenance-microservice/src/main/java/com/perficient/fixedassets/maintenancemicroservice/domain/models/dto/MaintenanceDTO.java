package com.perficient.fixedassets.maintenancemicroservice.domain.models.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MaintenanceDTO(
        Long id,
        Long assetId,
        String description,
        LocalDate maintenanceDate,
        String status,
        LocalDate finalizationDate
) {
}
