package com.perficient.fixedassets.newsmicroservice.infrastructure.jpa;

import com.perficient.fixedassets.newsmicroservice.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NewsJpaRepository extends JpaRepository<News, Long> {

    Collection<News> getNewsByAssetId(Long assetId);
}
