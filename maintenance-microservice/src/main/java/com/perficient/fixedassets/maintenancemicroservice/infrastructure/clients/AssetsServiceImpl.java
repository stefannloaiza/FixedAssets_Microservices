package com.perficient.fixedassets.maintenancemicroservice.infrastructure.clients;

import com.perficient.fixedassets.maintenancemicroservice.application.services.AssetsService;
import com.perficient.fixedassets.maintenancemicroservice.domain.models.dto.AssetDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
@Slf4j
public class AssetsServiceImpl implements AssetsService {

    private static final String ASSET_URL;
    private static final RestClient restClient;

    static {
        ASSET_URL = "http://localhost:8080/api/v1/assets";
        restClient = RestClient.create();
    }

    @Override
    public AssetDTO getAssetByAssetId(Long assetId) {
        return restClient.get()
                .uri("%s/%d".formatted(ASSET_URL, assetId))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    log.error("Error: status {} getting asset by assetId: {}", response.getStatusCode(), assetId);
                    throw new RestClientException("Error: status " + response.getStatusCode() + " getting asset by assetId: " + assetId);
                })
                .body(AssetDTO.class);
    }
}
