package com.perficient.fixedassets.newsmicroservice.infrastructure.repository;

import com.perficient.fixedassets.newsmicroservice.domain.entity.News;
import com.perficient.fixedassets.newsmicroservice.domain.repository.NewsRepository;
import com.perficient.fixedassets.newsmicroservice.infrastructure.jpa.NewsJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class NewsRepositoryImpl implements NewsRepository {

    private final NewsJpaRepository newsJpaRepository;

    @Override
    public Collection<News> getAllNews() {
        return newsJpaRepository.findAll();
    }

    @Override
    public News save(News news) {
        return newsJpaRepository.save(news);
    }

    @Override
    public News getNewsById(Long id) {
        return newsJpaRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<News> getNewsByAssetId(Long assetId) {
        return newsJpaRepository.getNewsByAssetId(assetId);
    }

    @Override
    public void deleteById(Long id) {
        newsJpaRepository.deleteById(id);
    }
}
