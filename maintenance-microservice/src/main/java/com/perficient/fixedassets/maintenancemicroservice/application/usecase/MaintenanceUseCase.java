package com.perficient.fixedassets.maintenancemicroservice.application.usecase;

import com.perficient.fixedassets.maintenancemicroservice.domain.models.dto.MaintenanceDTO;
import com.perficient.fixedassets.maintenancemicroservice.domain.models.response.MaintenanceResponse;
import org.springframework.http.ResponseEntity;

public interface MaintenanceUseCase {
    Iterable<MaintenanceDTO> getAll();

    MaintenanceDTO getById(Long id);

    Iterable<MaintenanceDTO> getByAssetId(Long assetId);

    ResponseEntity<MaintenanceResponse> createMaintenance(MaintenanceDTO maintenanceDTO);

    ResponseEntity<MaintenanceResponse> updateMaintenance(Long id, MaintenanceDTO maintenanceDTO);

    void deleteMaintenance(Long id);
}
