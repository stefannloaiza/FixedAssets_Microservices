package com.perficient.fixedassets.newsmicroservice.domain.models.response;

public record ErrorResponse(
        String code,
        String errorMessage
) {

}
