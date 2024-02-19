package com.perficient.fixedassets.maintenancemicroservice.application.services;

import com.perficient.fixedassets.maintenancemicroservice.domain.models.dto.AssetDTO;

public interface AssetsService {
    AssetDTO getAssetByAssetId(Long assetId);

}