package com.perficient.fixedassets.assetsmicroservice.application.usecase;

import com.perficient.fixedassets.assetsmicroservice.domain.models.dto.AssetDTO;
import com.perficient.fixedassets.assetsmicroservice.domain.models.response.AssetResponse;
import org.springframework.http.ResponseEntity;

public interface AssetUseCase {

    AssetDTO getAsset(Long id);

    Iterable<AssetDTO> getAllAssets();

    Iterable<AssetDTO> getAssetsByStatus(String status);

    ResponseEntity<AssetResponse> createAsset(AssetDTO assetDTO);

    ResponseEntity<AssetResponse> updateAsset(Long id, AssetDTO assetDTO);
}