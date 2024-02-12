package com.perficient.fixedassets.assetsmicroservice.infrastructure.repository;

import com.perficient.fixedassets.assetsmicroservice.domain.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    Collection<Asset> findByStatus(String status);
}