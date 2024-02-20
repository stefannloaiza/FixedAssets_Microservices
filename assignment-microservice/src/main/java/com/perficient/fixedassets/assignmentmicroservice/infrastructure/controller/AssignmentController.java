package com.perficient.fixedassets.assignmentmicroservice.infrastructure.controller;

import com.perficient.fixedassets.assignmentmicroservice.application.usecase.AssignmentUseCase;
import com.perficient.fixedassets.assignmentmicroservice.domain.models.dto.AssignmentDTO;
import com.perficient.fixedassets.assignmentmicroservice.domain.models.response.AssignmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assignment")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentUseCase assignmentUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AssignmentDTO>> getAssignments() {
        return ResponseEntity.ok(assignmentUseCase.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AssignmentResponse> createAssignment(@RequestBody AssignmentDTO assignments) {
        return assignmentUseCase.createAssignment(assignments);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AssignmentResponse> updateAssignment(@PathVariable Long id, @RequestBody AssignmentDTO assignments) {
        return assignmentUseCase.updateAssignment(id, assignments);
    }
}
