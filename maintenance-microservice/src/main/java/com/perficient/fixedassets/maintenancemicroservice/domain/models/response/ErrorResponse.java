package com.perficient.fixedassets.maintenancemicroservice.domain.models.response;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        String errorMessage,
        HttpStatus status
) {
}