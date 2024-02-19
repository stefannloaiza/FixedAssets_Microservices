package com.perficient.fixedassets.newsmicroservice.domain.models.response;

import java.util.List;

public record NewsReponse(
        String message,
        List<ErrorResponse> error
) {
}
