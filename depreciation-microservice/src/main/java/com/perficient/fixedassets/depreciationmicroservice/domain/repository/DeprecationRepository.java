package com.perficient.fixedassets.depreciationmicroservice.domain.repository;

import com.perficient.fixedassets.depreciationmicroservice.domain.entity.Deprecation;

import java.util.Collection;

public interface DeprecationRepository {

    Collection<Deprecation> findAll();

    Collection<Deprecation> findByAssetId(Long id);

    Deprecation findById(Long id);

    Deprecation save(Deprecation deprecation);

    void deleteById(Long id);
}