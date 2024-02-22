package com.perficient.fixedassets.maintenancemicroservice.infrastructure.listener;

import com.perficient.fixedassets.maintenancemicroservice.application.usecase.MaintenanceUseCase;
import com.perficient.fixedassets.maintenancemicroservice.domain.models.dto.MaintenanceDTO;
import com.perficient.fixedassets.maintenancemicroservice.domain.models.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import java.time.LocalDate;

@RequiredArgsConstructor
@Slf4j
public class MaintenanceListener {
    private final MaintenanceUseCase maintenanceUseCase;

    @KafkaListener(topics = "maintenance-topic", groupId = "maintenance")
    public void processNewMaintenance(Long assetId) {

        log.info("Processing new maintenance for asset: {}", assetId);

        MaintenanceDTO maintenanceDTO = MaintenanceDTO.builder()
                .assetId(assetId)
                .maintenanceDate(LocalDate.now())
                .status(Status.PENDING.toString())
                .finalizationDate(null)
                .description("Maintenance for asset: %d by news service".formatted(assetId))
                .build();

        maintenanceUseCase.createMaintenance(maintenanceDTO);
        log.info("New maintenance created for asset: {}", assetId);
    }

}
