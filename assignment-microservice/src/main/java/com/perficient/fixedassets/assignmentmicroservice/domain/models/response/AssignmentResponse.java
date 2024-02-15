package com.perficient.fixedassets.assignmentmicroservice.domain.models.response;

import java.util.List;

public record AssignmentResponse(
        String message,
        List<ErrorResponse> error
) {
}
