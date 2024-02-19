package com.perficient.fixedassets.depreciationmicroservice.infrastructure.jpa;

import com.perficient.fixedassets.depreciationmicroservice.domain.entity.Deprecation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DeprecationJpaRepository extends JpaRepository<Deprecation, Long> {

    Collection<Deprecation> findByAssetId(Long id);
}
