package com.perficient.fixedassets.usersmicroservice.domain.models.response;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        String errorMessage,
        HttpStatus status
) {
}