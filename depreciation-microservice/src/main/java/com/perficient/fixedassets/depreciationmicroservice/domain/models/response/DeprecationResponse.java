package com.perficient.fixedassets.depreciationmicroservice.domain.models.response;

import java.util.List;

public record DeprecationResponse(
        String message,
        List<ErrorResponse> error
) {
}
