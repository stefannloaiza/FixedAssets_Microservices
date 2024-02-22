package com.perficient.fixedassets.newsmicroservice.infrastructure.controller;

import com.perficient.fixedassets.newsmicroservice.application.usecase.NewsUseCase;
import com.perficient.fixedassets.newsmicroservice.domain.models.dto.NewsDTO;
import com.perficient.fixedassets.newsmicroservice.domain.models.response.NewsReponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsUseCase newsUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<NewsDTO> getAllNews() {
        return newsUseCase.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Long id) {
        NewsDTO news = newsUseCase.getNewsById(id);
        if (Objects.isNull(news)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(news);
    }

    @GetMapping("/asset/{assetId}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<NewsDTO> getNewsByAssetId(@PathVariable Long assetId) {
        return newsUseCase.getNewsByAssetId(assetId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NewsReponse> createNews(@RequestBody NewsDTO newsDTO) {
        return newsUseCase.createNews(newsDTO);
    }
}