package com.perficient.fixedassets.depreciationmicroservice.application.implementation;

import com.perficient.fixedassets.depreciationmicroservice.application.usecase.DeprecationUseCase;
import com.perficient.fixedassets.depreciationmicroservice.domain.entity.Deprecation;
import com.perficient.fixedassets.depreciationmicroservice.domain.mapper.DeprecationMapper;
import com.perficient.fixedassets.depreciationmicroservice.domain.models.dto.DeprecationDTO;
import com.perficient.fixedassets.depreciationmicroservice.domain.models.response.DeprecationResponse;
import com.perficient.fixedassets.depreciationmicroservice.domain.repository.DeprecationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeprecationUseCaseImpl implements DeprecationUseCase {

    private final DeprecationRepository deprecationRepository;

    @Override
    public Collection<DeprecationDTO> getDeprecations() {
        return deprecationRepository.findAll().stream()
                .map(DeprecationMapper.INSTANCE::toDeprecationDTO).toList();
    }

    @Override
    public Collection<DeprecationDTO> getDeprecationsByAssetId(Long assetId) {
        return deprecationRepository.findByAssetId(assetId).stream()
                .map(DeprecationMapper.INSTANCE::toDeprecationDTO).toList();
    }

    @Override
    public DeprecationDTO getDeprecation(Long id) {
        return DeprecationMapper.INSTANCE.toDeprecationDTO(deprecationRepository.findById(id));
    }

    @Override
    public ResponseEntity<DeprecationResponse> createDeprecation(DeprecationDTO deprecationDTO) {
        Deprecation deprecation = deprecationRepository.save(DeprecationMapper.INSTANCE.toDeprecation(deprecationDTO));
        log.info("Deprecation created: {}", deprecation);
        return ResponseEntity.ok(new DeprecationResponse("Deprecation created successfully", null));
    }

    @Override
    public ResponseEntity<DeprecationResponse> updateDeprecation(Long id, DeprecationDTO deprecationDTO) {
        if (Objects.isNull(deprecationRepository.findById(id))) {
            return ResponseEntity.badRequest().body(new DeprecationResponse("Deprecation not found", null));
        }

        Deprecation deprecation = deprecationRepository.save(DeprecationMapper.INSTANCE.toDeprecation(deprecationDTO));
        log.info("Deprecation updated: {}", deprecation);
        return ResponseEntity.ok(new DeprecationResponse("Deprecation updated successfully", null));
    }

    @Override
    public ResponseEntity<DeprecationResponse> deleteDeprecation(Long id) {

        if (Objects.isNull(deprecationRepository.findById(id))) {
            log.error("Deprecation not found: {}", id);
            return ResponseEntity.badRequest().body(new DeprecationResponse("Deprecation not found", null));
        }

        deprecationRepository.deleteById(id);
        log.info("Deprecation deleted: {}", id);
        return ResponseEntity.ok(new DeprecationResponse("Deprecation deleted successfully", null));
    }
}
