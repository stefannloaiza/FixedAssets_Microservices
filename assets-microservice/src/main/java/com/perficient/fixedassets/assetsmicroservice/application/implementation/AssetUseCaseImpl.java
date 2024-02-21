package com.perficient.fixedassets.assetsmicroservice.application.implementation;

import com.perficient.fixedassets.assetsmicroservice.application.usecase.AssetUseCase;
import com.perficient.fixedassets.assetsmicroservice.application.validations.AssetValidation;
import com.perficient.fixedassets.assetsmicroservice.domain.entity.Asset;
import com.perficient.fixedassets.assetsmicroservice.domain.mapper.AssetMapper;
import com.perficient.fixedassets.assetsmicroservice.domain.models.dto.AssetDTO;
import com.perficient.fixedassets.assetsmicroservice.domain.models.response.AssetResponse;
import com.perficient.fixedassets.assetsmicroservice.domain.models.response.ErrorResponse;
import com.perficient.fixedassets.assetsmicroservice.domain.repository.AssetRepository;
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

    private final AssetRepository assetRepository;

    @Override
    public AssetDTO getAsset(Long id) {
        return AssetMapper.INSTANCE.assetToAssetDTO(assetRepository.findById(id));
    }

    @Override
    public Iterable<AssetDTO> getAllAssets() {
        return assetRepository.findAll().stream()
                .map(AssetMapper.INSTANCE::assetToAssetDTO).toList();
    }

    @Override
    public Iterable<AssetDTO> getAssetsByStatus(String status) {
        return assetRepository.findByStatus(status)
                .stream().map(AssetMapper.INSTANCE::assetToAssetDTO).toList();
    }

    @Override
    public ResponseEntity<AssetResponse> createAsset(AssetDTO assetDTO) {
        Asset asset = AssetMapper.INSTANCE.assetDTOToAsset(assetDTO);

        List<ErrorResponse> errorsResponseList = checkAsset(asset);
        checkIfAssetCodeExist(asset, errorsResponseList);

        if (!errorsResponseList.isEmpty()) {
            return ResponseEntity.badRequest().body(new AssetResponse("Asset not created", errorsResponseList));
        }

        Asset save = assetRepository.save(asset);
        log.info("Asset created: {}", save);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AssetResponse("Asset created successfully", null));
    }

    @Override
    public ResponseEntity<AssetResponse> updateAsset(Long id, AssetDTO assetDTO) {
        Asset asset = assetRepository.findById(id);
        if (Objects.isNull(asset)) {
            log.error("Asset not found: {}", id);
            return ResponseEntity.badRequest().body(new AssetResponse("Asset not found", null));
        }
        
        Asset updated = AssetMapper.INSTANCE.updateAssetFromAssetDTO(assetDTO, asset);
        List<ErrorResponse> errorsResponseList = checkAsset(updated);
        if (!errorsResponseList.isEmpty()) {
            return ResponseEntity.badRequest().body(new AssetResponse("Asset not updated", errorsResponseList));
        }

        updated = assetRepository.save(updated);
        log.info("Asset updated: {}", updated);
        return ResponseEntity.ok(new AssetResponse("Asset updated successfully", null));
    }


    private static List<ErrorResponse> checkAsset(Asset asset) {
        return AssetValidation.checkAsset(asset);
    }

    private List<ErrorResponse> checkIfAssetCodeExist(Asset asset, List<ErrorResponse> errorsResponseList) {
        if (Objects.nonNull(assetRepository.findByCode(asset.getCode()))) {
            errorsResponseList.add(new ErrorResponse("Asset code already exists", HttpStatus.BAD_REQUEST));
        }
        return errorsResponseList;
    }
}
