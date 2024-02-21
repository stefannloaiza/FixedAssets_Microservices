package com.perficient.fixedassets.maintenancemicroservice.infrastructure.clients;

import com.perficient.fixedassets.maintenancemicroservice.domain.models.dto.AssetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gateway-msvc")
public interface AssetClient {

    @GetMapping("/api/v1/assets/{assetId}")
    AssetDTO getAssetByAssetId(@PathVariable Long assetId);
}