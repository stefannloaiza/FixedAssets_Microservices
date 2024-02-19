package com.perficient.fixedassets.maintenancemicroservice.domain.repository;

import com.perficient.fixedassets.maintenancemicroservice.domain.entity.Maintenance;

import java.util.Collection;

public interface MaintenanceRepository {

    Collection<Maintenance> findAll();

    Maintenance getById(Long id);

    Collection<Maintenance> getByAssetId(Long id);

    Maintenance save(Maintenance maintenance);

    void deleteById(Long id);
}
