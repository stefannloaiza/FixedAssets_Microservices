package com.perficient.fixedassets.assignmentmicroservice.application.services;

import com.perficient.fixedassets.assignmentmicroservice.domain.models.dto.AssetDTO;

public interface AssetsService {
    AssetDTO getAssetByAssetId(Long assetId);

    void sendStatusAssignedEvent(Long assetId);

    void updateAssetToAssigned(Long assetId);
}