package com.perficient.fixedassets.assignmentmicroservice.domain.models.response;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        String errorMessage,
        HttpStatus status
) {

}
