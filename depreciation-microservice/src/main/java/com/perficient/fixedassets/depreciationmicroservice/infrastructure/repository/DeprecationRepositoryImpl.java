package com.perficient.fixedassets.depreciationmicroservice.infrastructure.repository;

import com.perficient.fixedassets.depreciationmicroservice.domain.entity.Deprecation;
import com.perficient.fixedassets.depreciationmicroservice.domain.repository.DeprecationRepository;
import com.perficient.fixedassets.depreciationmicroservice.infrastructure.jpa.DeprecationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DeprecationRepositoryImpl implements DeprecationRepository {

    private final DeprecationJpaRepository deprecationJpaRepository;

    @Override
    public Collection<Deprecation> findAll() {
        return deprecationJpaRepository.findAll();
    }

    @Override
    public Collection<Deprecation> findByAssetId(Long id) {
        return deprecationJpaRepository.findByAssetId(id);
    }

    @Override
    public Deprecation findById(Long id) {
        return deprecationJpaRepository.findById(id).orElse(null);
    }

    @Override
    public Deprecation save(Deprecation deprecation) {
        return deprecationJpaRepository.save(deprecation);
    }

    @Override
    public void deleteById(Long id) {
        deprecationJpaRepository.deleteById(id);
    }
}
