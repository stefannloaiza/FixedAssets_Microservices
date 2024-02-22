package com.perficient.fixedassets.assignmentmicroservice.application.implementation;

import com.perficient.fixedassets.assignmentmicroservice.application.services.AssetsService;
import com.perficient.fixedassets.assignmentmicroservice.application.services.UsersService;
import com.perficient.fixedassets.assignmentmicroservice.application.usecase.AssignmentUseCase;
import com.perficient.fixedassets.assignmentmicroservice.application.validations.AssignmentValidations;
import com.perficient.fixedassets.assignmentmicroservice.domain.entity.Assignments;
import com.perficient.fixedassets.assignmentmicroservice.domain.mapper.AssignmentMapper;
import com.perficient.fixedassets.assignmentmicroservice.domain.models.dto.AssetDTO;
import com.perficient.fixedassets.assignmentmicroservice.domain.models.dto.AssignmentDTO;
import com.perficient.fixedassets.assignmentmicroservice.domain.models.enums.Status;
import com.perficient.fixedassets.assignmentmicroservice.domain.models.response.AssignmentResponse;
import com.perficient.fixedassets.assignmentmicroservice.domain.models.response.ErrorResponse;
import com.perficient.fixedassets.assignmentmicroservice.domain.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssignmentUseCaseImpl implements AssignmentUseCase {

    private final AssignmentRepository assignmentRepository;
    private final AssetsService assetsService;
    private final UsersService usersService;

    @Override
    public List<AssignmentDTO> getAll() {
        return assignmentRepository.findAll().stream().map(AssignmentMapper.INSTANCE::mapToAssignmentDTO).toList();
    }

    @Override
    public ResponseEntity<AssignmentResponse> createAssignment(AssignmentDTO assignmentDTO) {
        Assignments assignment = AssignmentMapper.INSTANCE.mapToAssignment(assignmentDTO);

        List<ErrorResponse> errorsResponseList = checkAssignment(assignment);

        checkIfExistAsset(assignment, errorsResponseList);
        checkIfExistUser(assignment, errorsResponseList);

        if (!errorsResponseList.isEmpty()) {
            return ResponseEntity.badRequest().body(new AssignmentResponse("Assignment validation failed", errorsResponseList));
        }

        assignment = assignmentRepository.save(assignment);
        log.info("Assignment created: {}", assignment);

        updateAsset(assignment.getAssetId());
        log.info("Asset updated: {}", assignment.getAssetId());

        return ResponseEntity.ok(new AssignmentResponse("Assignment created successfully", null));
    }

    @Override
    public ResponseEntity<AssignmentResponse> updateAssignment(Long id, AssignmentDTO assignmentDTO) {
        Assignments assignment = AssignmentMapper.INSTANCE.mapToAssignment(assignmentDTO);

        List<ErrorResponse> errorsResponseList = checkAssignment(assignment);

        checkIfExistAsset(assignment, errorsResponseList);
        checkIfExistUser(assignment, errorsResponseList);

        if (!errorsResponseList.isEmpty()) {
            return ResponseEntity.badRequest().body(new AssignmentResponse("Assignment validation failed", errorsResponseList));
        }

        Assignments update = assignmentRepository.save(assignment);
        log.info("Assignment updated: {}", update);


        return ResponseEntity.ok(new AssignmentResponse("Assignment updated successfully", null));
    }


    private static List<ErrorResponse> checkAssignment(Assignments assignments) {
        return AssignmentValidations.checkAssignment(assignments);
    }

    private void checkIfExistAsset(Assignments assignment, List<ErrorResponse> errorsResponseList) {
        try {
            AssetDTO assetDTO = assetsService.getAssetByAssetId(assignment.getAssetId());
            if (Objects.isNull(assetDTO)) {
                errorsResponseList.add(new ErrorResponse("Asset is not found", HttpStatus.NOT_FOUND));
                return;
            }
            if (assetDTO.assignmentStatus().equals(Status.ASSIGNED)) {
                errorsResponseList.add(new ErrorResponse("Asset is already assigned", HttpStatus.BAD_REQUEST));
            }
        } catch (RestClientException e) {
            errorsResponseList.add(new ErrorResponse(e.getMessage(), HttpStatus.FAILED_DEPENDENCY));
        }
    }

    private void checkIfExistUser(Assignments assignment, List<ErrorResponse> errorsResponseList) {
        try {
            if (Objects.equals(usersService.getUserByUserId(assignment.getUserId()), null)) {
                errorsResponseList.add(new ErrorResponse("User is not found", HttpStatus.NOT_FOUND));
            }
        } catch (RestClientException e) {
            errorsResponseList.add(new ErrorResponse(e.getMessage(), HttpStatus.FAILED_DEPENDENCY));
        }
    }

    private void updateAsset(Long assetId) {
        assetsService.updateAssetToAssigned(assetId);
    }
}