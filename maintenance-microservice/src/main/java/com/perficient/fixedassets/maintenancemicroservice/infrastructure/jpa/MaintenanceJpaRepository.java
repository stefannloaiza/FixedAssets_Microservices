package com.perficient.fixedassets.maintenancemicroservice.infrastructure.jpa;

import com.perficient.fixedassets.maintenancemicroservice.domain.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MaintenanceJpaRepository extends JpaRepository<Maintenance, Long> {
    Collection<Maintenance> findByAssetId(Long assetId);
}
