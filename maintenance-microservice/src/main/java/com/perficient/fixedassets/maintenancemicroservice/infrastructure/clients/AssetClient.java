package com.perficient.fixedassets.maintenancemicroservice.infrastructure.clients;

import com.perficient.fixedassets.maintenancemicroservice.domain.models.dto.AssetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "assets-msvc")
public interface AssetClient {

    @GetMapping("/asset/{assetId}")
    AssetDTO getAssetByAssetId(Long assetId);
}
