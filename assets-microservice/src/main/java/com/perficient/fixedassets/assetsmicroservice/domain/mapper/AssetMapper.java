package com.perficient.fixedassets.assetsmicroservice.domain.mapper;

import com.perficient.fixedassets.assetsmicroservice.domain.entity.Asset;
import com.perficient.fixedassets.assetsmicroservice.domain.models.dto.AssetDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AssetMapper {

    AssetMapper INSTANCE = Mappers.getMapper(AssetMapper.class);

    Asset assetDTOToAsset(AssetDTO assetDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Asset updateAssetFromAssetDTO(AssetDTO assetDTO, @MappingTarget Asset asset);

    AssetDTO assetToAssetDTO(Asset asset);
}
