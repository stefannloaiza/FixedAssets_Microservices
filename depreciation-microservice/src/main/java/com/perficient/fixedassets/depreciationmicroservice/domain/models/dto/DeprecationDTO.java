package com.perficient.fixedassets.depreciationmicroservice.domain.models.dto;

public record DeprecationDTO(
        Long id,
        Long assetId,
        Integer year,
        Double depreciationRate,
        Double depreciationValue,
        String depreciationType,
        Double actualValue,
        Double residualValue,
        Integer estimatedLifeTime
) {
}
