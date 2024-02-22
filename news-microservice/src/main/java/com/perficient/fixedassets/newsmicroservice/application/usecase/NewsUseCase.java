package com.perficient.fixedassets.newsmicroservice.application.usecase;

import com.perficient.fixedassets.newsmicroservice.domain.models.dto.NewsDTO;
import com.perficient.fixedassets.newsmicroservice.domain.models.response.NewsReponse;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface NewsUseCase {

    Collection<NewsDTO> getAll();

    NewsDTO getNewsById(Long id);

    Collection<NewsDTO> getNewsByAssetId(Long assetId);

    ResponseEntity<NewsReponse> createNews(NewsDTO newsDTO);
}