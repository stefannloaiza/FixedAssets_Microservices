package com.perficient.fixedassets.assetsmicroservice.infrastructure.repository;

import com.perficient.fixedassets.assetsmicroservice.domain.entity.Asset;
import com.perficient.fixedassets.assetsmicroservice.domain.repository.AssetRepository;
import com.perficient.fixedassets.assetsmicroservice.infrastructure.jpa.AssetJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AssetRepositoryImpl implements AssetRepository {

    private final AssetJpaRepository assetJpaRepository;

    @Override
    public Asset save(Asset asset) {
        return assetJpaRepository.save(asset);
    }

    @Override
    public Asset findById(Long id) {
        return assetJpaRepository.findById(id).orElse(null);
    }

    @Override
    public Asset findByCode(String code) {
        return assetJpaRepository.findByCode(code).orElse(null);
    }

    @Override
    public Collection<Asset> findAll() {
        return assetJpaRepository.findAll();
    }

    @Override
    public Collection<Asset> findByStatus(String status) {
        return assetJpaRepository.findByStatus(status);
    }


    @Override
    public void deleteById(Long id) {
        assetJpaRepository.deleteById(id);
    }
}
