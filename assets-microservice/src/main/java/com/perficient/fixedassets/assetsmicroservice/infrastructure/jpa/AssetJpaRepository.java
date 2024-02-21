package com.perficient.fixedassets.assetsmicroservice.infrastructure.jpa;

import com.perficient.fixedassets.assetsmicroservice.domain.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AssetJpaRepository extends JpaRepository<Asset, Long> {
    Collection<Asset> findByStatus(String status);

    Optional<Asset> findByCode(String code);
}