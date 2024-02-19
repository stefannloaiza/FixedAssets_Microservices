package com.perficient.fixedassets.depreciationmicroservice.application.usecase;

import com.perficient.fixedassets.depreciationmicroservice.domain.models.dto.DeprecationDTO;
import com.perficient.fixedassets.depreciationmicroservice.domain.models.response.DeprecationResponse;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface DeprecationUseCase {

    Collection<DeprecationDTO> getDeprecations();

    Collection<DeprecationDTO> getDeprecationsByAssetId(Long id);

    DeprecationDTO getDeprecation(Long id);

    ResponseEntity<DeprecationResponse> createDeprecation(DeprecationDTO deprecationDTO);

    ResponseEntity<DeprecationResponse> updateDeprecation(Long id, DeprecationDTO deprecationDTO);

    ResponseEntity<DeprecationResponse> deleteDeprecation(Long id);
}
