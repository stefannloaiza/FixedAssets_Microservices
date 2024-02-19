package com.perficient.fixedassets.newsmicroservice.application.usecase;

import com.perficient.fixedassets.newsmicroservice.domain.models.dto.NewsDTO;
import com.perficient.fixedassets.newsmicroservice.domain.models.response.NewsReponse;

import java.util.Collection;

public interface NewsUseCase {

    Collection<NewsDTO> getAll();

    NewsDTO getNewsById(Long id);

    Collection<NewsDTO> getNewsByAssetId(Long assetId);

    NewsReponse createNews(NewsDTO newsDTO);

    void deleteNewsById(Long id);

    void generateNewsNotificationEvents();
}
