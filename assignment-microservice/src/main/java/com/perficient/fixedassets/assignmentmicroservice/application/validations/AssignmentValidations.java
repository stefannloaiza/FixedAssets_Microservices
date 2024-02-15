package com.perficient.fixedassets.assignmentmicroservice.application.validations;

import com.perficient.fixedassets.assignmentmicroservice.domain.entity.Assignments;
import com.perficient.fixedassets.assignmentmicroservice.domain.models.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class AssignmentValidations {

    private static List<ErrorResponse> errorResponseList;

    static {
        errorResponseList = new ArrayList<>();
    }

    private AssignmentValidations() {
    }

    public static List<ErrorResponse> checkAssignment(Assignments assignments) {

        errorResponseList = new ArrayList<>();

        if (assignments.getAssignmentDate().isBefore(LocalDate.now())) {
            errorResponseList.add(new ErrorResponse("Invalid assignment date: " + assignments.getAssignmentDate(), HttpStatus.BAD_REQUEST));
            log.error("Invalid assignment date: {}", assignments.getAssignmentDate());
        }

        if (Objects.nonNull(assignments.getAssetId()) && assignments.getAssetId() == 0L) {
            errorResponseList.add(new ErrorResponse("Asset cant be null", HttpStatus.BAD_REQUEST));
            log.error("Asset not found: {}", assignments.getAssetId());
        }

        if (Objects.nonNull(assignments.getUserId()) && assignments.getUserId() == 0L) {
            errorResponseList.add(new ErrorResponse("User cant be null", HttpStatus.BAD_REQUEST));
            log.error("User not found: {}", assignments.getUserId());
        }

        return errorResponseList;
    }
}
