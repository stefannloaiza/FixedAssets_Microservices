package com.perficient.fixedassets.newsmicroservice.application.validations;

import com.perficient.fixedassets.newsmicroservice.domain.entity.News;
import com.perficient.fixedassets.newsmicroservice.domain.models.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class NewsValidation {

    private static List<ErrorResponse> errorResponseList;

    static {
        errorResponseList = new ArrayList<>();
    }

    private NewsValidation() {
    }

    public static List<ErrorResponse> validateNews(News news) {
        errorResponseList = new ArrayList<>();

        if (Objects.isNull(news.getAssetId())) {
            errorResponseList.add(new ErrorResponse("NEWS_1", "Asset cant be null"));
            log.error("Asset not found: {}", news.getAssetId());
        }

        if (Objects.isNull(news.getTitle())) {
            errorResponseList.add(new ErrorResponse("NEWS_2", "Title cant be null"));
            log.error("Title not found: {}", news.getTitle());
        }

        if (Objects.isNull(news.getRegistrationDate())) {
            errorResponseList.add(new ErrorResponse("NEWS_3", "Registration date cant be null"));
            log.error("Registration date not found: {}", news.getRegistrationDate());
        }

        if (Objects.isNull(news.getStatus())) {
            errorResponseList.add(new ErrorResponse("NEWS_4", "Status cant be null"));
            log.error("Status not found: {}", news.getStatus());
        }

        if (Objects.isNull(news.getDescription())) {
            errorResponseList.add(new ErrorResponse("NEWS_5", "Description cant be null"));
            log.error("Description not found: {}", news.getDescription());
        }

        if (Objects.isNull(news.getUserAssigned())) {
            errorResponseList.add(new ErrorResponse("NEWS_6", "User assigned cant be null"));
            log.error("User assigned not found: {}", news.getUserAssigned());
        }

        if (Objects.isNull(news.getUserNotifier())) {
            errorResponseList.add(new ErrorResponse("NEWS_7", "User notifier cant be null"));
            log.error("User notifier not found: {}", news.getUserNotifier());
        }
        return errorResponseList;
    }

}