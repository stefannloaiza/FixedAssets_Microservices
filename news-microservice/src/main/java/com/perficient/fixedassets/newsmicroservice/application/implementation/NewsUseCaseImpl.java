package com.perficient.fixedassets.newsmicroservice.application.implementation;

import com.perficient.fixedassets.newsmicroservice.application.usecase.NewsUseCase;
import com.perficient.fixedassets.newsmicroservice.application.validations.NewsValidation;
import com.perficient.fixedassets.newsmicroservice.domain.entity.News;
import com.perficient.fixedassets.newsmicroservice.domain.mapper.NewsMapper;
import com.perficient.fixedassets.newsmicroservice.domain.models.dto.NewsDTO;
import com.perficient.fixedassets.newsmicroservice.domain.models.response.ErrorResponse;
import com.perficient.fixedassets.newsmicroservice.domain.models.response.NewsReponse;
import com.perficient.fixedassets.newsmicroservice.domain.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsUseCaseImpl implements NewsUseCase {

    private final NewsRepository newsRepository;
    private final KafkaTemplate<String, News> kafkaTemplate;

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
    public ResponseEntity<NewsReponse> createNews(NewsDTO newsDTO) {
        News news = NewsMapper.INSTANCE.newsDTOToNews(newsDTO);

        List<ErrorResponse> errorResponseList = checkNews(news);
        if (!errorResponseList.isEmpty()) {
            return ResponseEntity.badRequest().body(new NewsReponse("Failed to create news", HttpStatus.BAD_REQUEST, errorResponseList));
        }

        news = newsRepository.save(news);
        log.info("News created: {}", news);

        // generate maintenance for this new
        sendNewMaintenanceEvent(news);

        return ResponseEntity.ok(new NewsReponse("News created successfully", HttpStatus.CREATED, null));
    }

    private List<ErrorResponse> checkNews(News news) {
        return NewsValidation.validateNews(news);
    }

    private void sendNewMaintenanceEvent(News news) {
        // use kafka to generate new maintenance for the asset.
        kafkaTemplate.send("maintenance-topic", news);
        log.info("New maintenance generated for asset: {}", news.getAssetId());
    }
}
