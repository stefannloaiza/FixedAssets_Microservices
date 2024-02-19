package com.perficient.fixedassets.maintenancemicroservice.domain.models.dto;

import java.time.LocalDate;

public record MaintenanceDTO(
        Long id,
        Long assetId,
        String description,
        LocalDate maintenanceDate,
        String status,
        LocalDate finalizationDate
) {
}
