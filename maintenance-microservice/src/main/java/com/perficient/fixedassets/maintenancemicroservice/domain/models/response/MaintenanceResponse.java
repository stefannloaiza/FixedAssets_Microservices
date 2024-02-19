package com.perficient.fixedassets.maintenancemicroservice.domain.models.response;

import java.util.List;

public record MaintenanceResponse(
        String MaintenanceMessage,
        List<ErrorResponse> error
) {
}