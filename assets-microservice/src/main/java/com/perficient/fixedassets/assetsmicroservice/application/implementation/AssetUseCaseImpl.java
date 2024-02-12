package com.perficient.fixedassets.assetsmicroservice.application.implementation;

import com.perficient.fixedassets.assetsmicroservice.application.usecase.AssetUseCase;
import com.perficient.fixedassets.assetsmicroservice.domain.entity.Asset;
import com.perficient.fixedassets.assetsmicroservice.domain.mapper.AssetMapper;
import com.perficient.fixedassets.assetsmicroservice.domain.models.dto.AssetDTO;
import com.perficient.fixedassets.assetsmicroservice.infrastructure.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetUseCaseImpl implements AssetUseCase {

    private final AssetRepository assetRepository;

    @Override
    public AssetDTO getAsset(Long id) {
        return assetRepository.findById(id)
                .map(AssetMapper.INSTANCE::assetToAssetDTO)
                .orElse(null);
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
    public void createAsset(AssetDTO assetDTO) {
        Asset save = assetRepository.save(AssetMapper.INSTANCE.assetDTOToAsset(assetDTO));
        log.info("Asset created: {}", save);
    }

    @Override
    public Boolean updateAsset(Long id, AssetDTO assetDTO) {
        Asset asset = assetRepository.findById(id).orElse(null);
        if (Objects.isNull(asset)) {
            log.error("Asset not found: {}", id);
            return false;
        }
        assetRepository.save(AssetMapper.INSTANCE.updateAssetFromAssetDTO(assetDTO, asset));
        log.info("Asset updated: {}", asset);
        return true;
    }
}
