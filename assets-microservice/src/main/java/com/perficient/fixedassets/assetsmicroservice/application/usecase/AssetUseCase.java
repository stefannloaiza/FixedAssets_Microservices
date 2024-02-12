package com.perficient.fixedassets.assetsmicroservice.application.usecase;

import com.perficient.fixedassets.assetsmicroservice.domain.models.dto.AssetDTO;

public interface AssetUseCase {

    AssetDTO getAsset(Long id);

    Iterable<AssetDTO> getAllAssets();

    Iterable<AssetDTO> getAssetsByStatus(String status);

    void createAsset(AssetDTO assetDTO);

    Boolean updateAsset(Long id, AssetDTO assetDTO);
}