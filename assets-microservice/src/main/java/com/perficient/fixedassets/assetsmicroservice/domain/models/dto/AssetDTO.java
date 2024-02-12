package com.perficient.fixedassets.assetsmicroservice.domain.models.dto;

import com.perficient.fixedassets.assetsmicroservice.domain.models.enums.Status;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AssetDTO(
        String name, String description, Status status, LocalDate acquisitionDate, Double acquisitionCost
) {
}