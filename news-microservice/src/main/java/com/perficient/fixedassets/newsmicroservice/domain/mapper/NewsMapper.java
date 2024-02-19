package com.perficient.fixedassets.newsmicroservice.domain.mapper;

import com.perficient.fixedassets.newsmicroservice.domain.entity.News;
import com.perficient.fixedassets.newsmicroservice.domain.models.dto.NewsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDTO newsToNewsDTO(News news);

    News newsDTOToNews(NewsDTO newsDTO);
}
