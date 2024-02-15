package com.perficient.fixedassets.assignmentmicroservice.domain.models.dto;

import java.time.LocalDate;

public record AssignmentDTO(
        Long assetId,
        Long userId,
        LocalDate assignmentDate
) {
}
