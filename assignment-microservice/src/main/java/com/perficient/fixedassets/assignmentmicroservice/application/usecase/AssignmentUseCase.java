package com.perficient.fixedassets.assignmentmicroservice.application.usecase;

import com.perficient.fixedassets.assignmentmicroservice.domain.models.dto.AssignmentDTO;
import com.perficient.fixedassets.assignmentmicroservice.domain.models.response.AssignmentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssignmentUseCase {
    List<AssignmentDTO> getAll();

    ResponseEntity<AssignmentResponse> createAssignment(AssignmentDTO assignments);

    ResponseEntity<AssignmentResponse> updateAssignment(Long id, AssignmentDTO assignments);
}