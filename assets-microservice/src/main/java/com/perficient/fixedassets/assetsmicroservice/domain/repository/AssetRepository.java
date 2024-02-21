package com.perficient.fixedassets.assetsmicroservice.domain.repository;

import com.perficient.fixedassets.assetsmicroservice.domain.entity.Asset;

import java.util.Collection;

public interface AssetRepository {
    Asset save(Asset asset);

    Asset findById(Long id);

    Collection<Asset> findAll();

    Collection<Asset> findByStatus(String status);

    void deleteById(Long id);
}
