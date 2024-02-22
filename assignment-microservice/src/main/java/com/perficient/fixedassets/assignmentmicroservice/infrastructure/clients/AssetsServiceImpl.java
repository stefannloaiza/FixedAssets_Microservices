package com.perficient.fixedassets.assignmentmicroservice.infrastructure.clients;

import com.perficient.fixedassets.assignmentmicroservice.application.services.AssetsService;
import com.perficient.fixedassets.assignmentmicroservice.domain.models.dto.AssetDTO;
import com.perficient.fixedassets.assignmentmicroservice.domain.singleton.RestClientSingleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetsServiceImpl implements AssetsService {

    private static final String ASSET_URL;
    private static final String ASSIGN_URL;
    private static final RestClient restClient;

    private final KafkaTemplate<String, Long> kafkaTemplate;

    static {
        ASSET_URL = "http://localhost:8080/api/v1/assets";
        ASSIGN_URL = "%s/assign".formatted(ASSET_URL);
        restClient = RestClientSingleton.getInstance();
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
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    log.error("Error: status {} getting asset by assetId: {}", response.getStatusCode(), assetId);
                    throw new RestClientException("Error: status " + response.getStatusCode() + " getting asset by assetId: " + assetId);
                })
                .body(AssetDTO.class);
    }

    @Override
    public void updateAssetToAssigned(Long assetId) {
        restClient.patch()
                .uri("%s/%d".formatted(ASSIGN_URL, assetId))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    log.error("Error: status {} updating asset to assigned: {}", response.getStatusCode(), assetId);
                    throw new RestClientException("Error: status " + response.getStatusCode() + " updating asset to assigned: " + assetId);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    log.error("Error: status {} updating asset to assigned: {}", response.getStatusCode(), assetId);
                    throw new RestClientException("Error: status " + response.getStatusCode() + " updating asset to assigned: " + assetId);
                })
        ;
    }

    @Override
    public void sendStatusAssignedEvent(Long assetId) {
        kafkaTemplate.send("status-assigned", assetId);
    }
}
