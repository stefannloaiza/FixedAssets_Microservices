package com.perficient.fixedassets.maintenancemicroservice.application.implementation;

import com.perficient.fixedassets.maintenancemicroservice.application.services.AssetsService;
import com.perficient.fixedassets.maintenancemicroservice.application.usecase.MaintenanceUseCase;
import com.perficient.fixedassets.maintenancemicroservice.domain.entity.Maintenance;
import com.perficient.fixedassets.maintenancemicroservice.domain.mapper.MaintenanceMapper;
import com.perficient.fixedassets.maintenancemicroservice.domain.models.dto.MaintenanceDTO;
import com.perficient.fixedassets.maintenancemicroservice.domain.models.response.MaintenanceResponse;
import com.perficient.fixedassets.maintenancemicroservice.domain.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaintenanceUseCaseImpl implements MaintenanceUseCase {

    private final MaintenanceRepository maintenanceRepository;
    private final AssetsService assetsService;

    @Override
    public List<MaintenanceDTO> getAll() {
        return maintenanceRepository.findAll().stream().map(MaintenanceMapper.INSTANCE::maintenanceToMaintenanceDTO).toList();
    }

    @Override
    public MaintenanceDTO getById(Long id) {
        return MaintenanceMapper.INSTANCE.maintenanceToMaintenanceDTO(maintenanceRepository.getById(id));
    }

    @Override
    public List<MaintenanceDTO> getByAssetId(Long assetId) {
        return maintenanceRepository.getByAssetId(assetId).stream().map(MaintenanceMapper.INSTANCE::maintenanceToMaintenanceDTO).toList();
    }

    @Override
    public ResponseEntity<MaintenanceResponse> createMaintenance(MaintenanceDTO maintenanceDTO) {
        Maintenance maintenance = MaintenanceMapper.INSTANCE.maintenanceDTOToMaintenance(maintenanceDTO);

        if (Objects.isNull(maintenance.getAssetId())) {
            return ResponseEntity.badRequest().body(new MaintenanceResponse("AssetId is required", null));
        }

        if (Objects.isNull(assetsService.getAssetByAssetId(maintenance.getAssetId()))) {
            return ResponseEntity.badRequest().body(new MaintenanceResponse("Asset not found", null));
        }

        Maintenance newMaintenance = maintenanceRepository.save(maintenance);
        log.info("Maintenance created: {}", newMaintenance);
        return ResponseEntity.ok(new MaintenanceResponse("Maintenance created successfully", null));
    }

    @Override
    public ResponseEntity<MaintenanceResponse> updateMaintenance(Long id, MaintenanceDTO maintenanceDTO) {

        Maintenance maintenance = maintenanceRepository.save(MaintenanceMapper.INSTANCE.maintenanceDTOToMaintenance(maintenanceDTO));

        log.info("Maintenance updated: {}", maintenance);
        return ResponseEntity.ok(new MaintenanceResponse("Maintenance updated successfully", null));
    }

    @Override
    public void deleteMaintenance(Long id) {
        maintenanceRepository.deleteById(id);
        log.info("Maintenance deleted: {}", id);
    }


}
