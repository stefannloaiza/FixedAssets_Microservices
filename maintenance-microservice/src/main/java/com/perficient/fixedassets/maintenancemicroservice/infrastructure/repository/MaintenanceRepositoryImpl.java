package com.perficient.fixedassets.maintenancemicroservice.infrastructure.repository;

import com.perficient.fixedassets.maintenancemicroservice.domain.entity.Maintenance;
import com.perficient.fixedassets.maintenancemicroservice.domain.repository.MaintenanceRepository;
import com.perficient.fixedassets.maintenancemicroservice.infrastructure.jpa.MaintenanceJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class MaintenanceRepositoryImpl implements MaintenanceRepository {

    private final MaintenanceJpaRepository maintenanceJpaRepository;

    @Override
    public Collection<Maintenance> findAll() {
        return maintenanceJpaRepository.findAll();
    }

    @Override
    public Maintenance getById(Long id) {
        return maintenanceJpaRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Maintenance> getByAssetId(Long id) {
        return maintenanceJpaRepository.findByAssetId(id);
    }

    @Override
    public Maintenance save(Maintenance maintenance) {
        return maintenanceJpaRepository.save(maintenance);
    }

    @Override
    public void deleteById(Long id) {
        maintenanceJpaRepository.deleteById(id);
    }
}
