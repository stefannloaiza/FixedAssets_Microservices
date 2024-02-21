package com.perficient.fixedassets.assetsmicroservice.domain.models.response;

import java.util.List;

public record AssetResponse(
        String message,
        List<ErrorResponse> error
) {
}
