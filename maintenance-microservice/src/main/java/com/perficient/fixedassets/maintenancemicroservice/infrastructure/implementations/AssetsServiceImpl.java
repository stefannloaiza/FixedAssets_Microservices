package com.perficient.fixedassets.maintenancemicroservice.infrastructure.implementations;

import com.perficient.fixedassets.maintenancemicroservice.application.services.AssetsService;
import com.perficient.fixedassets.maintenancemicroservice.domain.models.dto.AssetDTO;
import com.perficient.fixedassets.maintenancemicroservice.infrastructure.clients.AssetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetsServiceImpl implements AssetsService {

    private final AssetClient assetClient;

    @Override
    public AssetDTO getAssetByAssetId(Long assetId) {
        return assetClient.getAssetByAssetId(assetId);
    }
}
