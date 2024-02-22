package com.perficient.fixedassets.newsmicroservice.domain.repository;

import com.perficient.fixedassets.newsmicroservice.domain.entity.News;

import java.util.Collection;

public interface NewsRepository {

    Collection<News> getAllNews();

    News save(News news);

    News getNewsById(Long id);

    Collection<News> getNewsByAssetId(Long assetId);
}