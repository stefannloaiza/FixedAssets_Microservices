package com.perficient.fixedassets.newsmicroservice.application.implementation;

import com.perficient.fixedassets.newsmicroservice.application.usecase.NewsUseCase;
import com.perficient.fixedassets.newsmicroservice.domain.entity.News;
import com.perficient.fixedassets.newsmicroservice.domain.mapper.NewsMapper;
import com.perficient.fixedassets.newsmicroservice.domain.models.dto.NewsDTO;
import com.perficient.fixedassets.newsmicroservice.domain.models.response.NewsReponse;
import com.perficient.fixedassets.newsmicroservice.domain.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsUseCaseImpl implements NewsUseCase {

    private final NewsRepository newsRepository;

    @Override
    public Collection<NewsDTO> getAll() {
        return newsRepository.getAllNews().stream()
                .map(NewsMapper.INSTANCE::newsToNewsDTO).toList();
    }

    @Override
    public NewsDTO getNewsById(Long id) {
        return NewsMapper.INSTANCE.newsToNewsDTO(newsRepository.getNewsById(id));
    }

    @Override
    public Collection<NewsDTO> getNewsByAssetId(Long assetId) {
        return newsRepository.getNewsByAssetId(assetId).stream()
                .map(NewsMapper.INSTANCE::newsToNewsDTO).toList();
    }


    @Override
    public NewsReponse createNews(NewsDTO newsDTO) {
        News newsSaved = newsRepository.save(NewsMapper.INSTANCE.newsDTOToNews(newsDTO));
        log.info("News created: {}", newsSaved);

        return new NewsReponse("News created successfully!", null);
    }

    @Override
    public void deleteNewsById(Long id) {
        newsRepository.deleteById(id);
        log.info("News deleted: {}", id);
    }

    @Override
    public void generateNewsNotificationEvents() {
        // use kafka to nofity all users
        log.info("Generating news notification events...");
        log.info("News notification events generated.");
    }
}
