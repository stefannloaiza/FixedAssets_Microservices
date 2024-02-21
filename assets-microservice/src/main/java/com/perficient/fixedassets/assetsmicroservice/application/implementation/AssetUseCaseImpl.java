package com.perficient.fixedassets.assetsmicroservice.application.implementation;

import com.perficient.fixedassets.assetsmicroservice.application.usecase.AssetUseCase;
import com.perficient.fixedassets.assetsmicroservice.application.validations.AssetValidation;
import com.perficient.fixedassets.assetsmicroservice.domain.entity.Asset;
import com.perficient.fixedassets.assetsmicroservice.domain.mapper.AssetMapper;
import com.perficient.fixedassets.assetsmicroservice.domain.models.dto.AssetDTO;
import com.perficient.fixedassets.assetsmicroservice.domain.models.response.AssetResponse;
import com.perficient.fixedassets.assetsmicroservice.domain.models.response.ErrorResponse;
import com.perficient.fixedassets.assetsmicroservice.infrastructure.jpa.AssetJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetUseCaseImpl implements AssetUseCase {

    private final AssetJpaRepository assetJpaRepository;

    @Override
    public AssetDTO getAsset(Long id) {
        return assetJpaRepository.findById(id)
                .map(AssetMapper.INSTANCE::assetToAssetDTO)
                .orElse(null);
    }

    @Override
    public Iterable<AssetDTO> getAllAssets() {
        return assetJpaRepository.findAll().stream()
                .map(AssetMapper.INSTANCE::assetToAssetDTO).toList();
    }

    @Override
    public Iterable<AssetDTO> getAssetsByStatus(String status) {
        return assetJpaRepository.findByStatus(status)
                .stream().map(AssetMapper.INSTANCE::assetToAssetDTO).toList();
    }

    @Override
    public ResponseEntity<AssetResponse> createAsset(AssetDTO assetDTO) {
        Asset asset = AssetMapper.INSTANCE.assetDTOToAsset(assetDTO);

        List<ErrorResponse> errorsResponseList = checkAsset(asset);
        if (!errorsResponseList.isEmpty()) {
            return ResponseEntity.badRequest().body(new AssetResponse("Asset not created", errorsResponseList));
        }

        Asset save = assetJpaRepository.save(asset);
        log.info("Asset created: {}", save);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AssetResponse("Asset created successfully", null));
    }

    @Override
    public ResponseEntity<AssetResponse> updateAsset(Long id, AssetDTO assetDTO) {
        Asset asset = assetJpaRepository.findById(id).orElse(null);
        if (Objects.isNull(asset)) {
            log.error("Asset not found: {}", id);
            return ResponseEntity.badRequest().body(new AssetResponse("Asset not found", null));
        }
        assetJpaRepository.save(AssetMapper.INSTANCE.updateAssetFromAssetDTO(assetDTO, asset));
        log.info("Asset updated: {}", asset);
        return ResponseEntity.ok(new AssetResponse("Asset updated successfully", null));
    }


    private static List<ErrorResponse> checkAsset(Asset asset) {
        return AssetValidation.checkAsset(asset);
    }
}
