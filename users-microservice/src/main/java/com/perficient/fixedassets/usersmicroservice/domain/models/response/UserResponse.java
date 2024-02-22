package com.perficient.fixedassets.usersmicroservice.domain.models.response;

import java.util.List;

public record UserResponse(
        String message,
        List<ErrorResponse> error
) {
}