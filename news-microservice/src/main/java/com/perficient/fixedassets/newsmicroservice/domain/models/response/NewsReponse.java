package com.perficient.fixedassets.newsmicroservice.domain.models.response;

import org.springframework.http.HttpStatus;

import java.util.List;

public record NewsReponse(
        String message,
        HttpStatus status,
        List<ErrorResponse> error
) {
}
