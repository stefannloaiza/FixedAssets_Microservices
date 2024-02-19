package com.perficient.fixedassets.depreciationmicroservice.infrastructure.controller;

import com.perficient.fixedassets.depreciationmicroservice.application.usecase.DeprecationUseCase;
import com.perficient.fixedassets.depreciationmicroservice.domain.models.dto.DeprecationDTO;
import com.perficient.fixedassets.depreciationmicroservice.domain.models.response.DeprecationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/deprecations")
@RequiredArgsConstructor
public class DeprecationController {

    private final DeprecationUseCase deprecationUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<DeprecationDTO> getDeprecations() {
        return deprecationUseCase.getDeprecations();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeprecationDTO getDeprecation(@PathVariable Long id) {
        return deprecationUseCase.getDeprecation(id);
    }

    @GetMapping("/asset/{assetId}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<DeprecationDTO> getDeprecationsByAssetId(@PathVariable Long assetId) {
        return deprecationUseCase.getDeprecationsByAssetId(assetId);
    }

    public ResponseEntity<DeprecationResponse> createDeprecation(DeprecationDTO deprecationDTO) {
        return deprecationUseCase.createDeprecation(deprecationDTO);
    }

    public ResponseEntity<DeprecationResponse> updateDeprecation(Long id, DeprecationDTO deprecationDTO) {
        return deprecationUseCase.updateDeprecation(id, deprecationDTO);
    }

    public ResponseEntity<DeprecationResponse> deleteDeprecation(Long id) {
        return deprecationUseCase.deleteDeprecation(id);
    }
}
