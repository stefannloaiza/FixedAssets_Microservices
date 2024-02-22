package com.perficient.fixedassets.assetsmicroservice.infrastructure.listener;

import com.perficient.fixedassets.assetsmicroservice.application.usecase.AssetUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetListener {

    private final AssetUseCase assetUseCase;

    @KafkaListener(topics = "status-assigned", groupId = "asset")
    public void processStatusAssigned(Long assetId) {
        assetUseCase.assignAsset(assetId);
        log.info("Asset with id {} has been assigned", assetId);
    }
}
