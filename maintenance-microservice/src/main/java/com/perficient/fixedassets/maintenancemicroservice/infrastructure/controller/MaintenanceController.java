package com.perficient.fixedassets.maintenancemicroservice.infrastructure.controller;

import com.perficient.fixedassets.maintenancemicroservice.application.usecase.MaintenanceUseCase;
import com.perficient.fixedassets.maintenancemicroservice.domain.models.dto.MaintenanceDTO;
import com.perficient.fixedassets.maintenancemicroservice.domain.models.response.MaintenanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceUseCase maintenanceUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Iterable<MaintenanceDTO>> getAll() {
        return ResponseEntity.ok(maintenanceUseCase.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MaintenanceDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceUseCase.getById(id));
    }

    @GetMapping("/asset/{assetId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Iterable<MaintenanceDTO>> getByAssetId(@PathVariable Long assetId) {
        return ResponseEntity.ok(maintenanceUseCase.getByAssetId(assetId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MaintenanceResponse> createMaintenance(@RequestBody MaintenanceDTO maintenanceDTO) {
        return maintenanceUseCase.createMaintenance(maintenanceDTO);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MaintenanceResponse> updateMaintenance(@PathVariable Long id, @RequestBody MaintenanceDTO maintenanceDTO) {
        return maintenanceUseCase.updateMaintenance(id, maintenanceDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteMaintenance(@PathVariable Long id) {
        maintenanceUseCase.deleteMaintenance(id);
        return ResponseEntity.ok("Maintenance deleted successfully");
    }
}
