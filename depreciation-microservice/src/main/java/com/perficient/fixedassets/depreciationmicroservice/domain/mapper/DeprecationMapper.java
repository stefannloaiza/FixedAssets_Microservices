package com.perficient.fixedassets.depreciationmicroservice.domain.mapper;

import com.perficient.fixedassets.depreciationmicroservice.domain.entity.Deprecation;
import com.perficient.fixedassets.depreciationmicroservice.domain.models.dto.DeprecationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeprecationMapper {

    DeprecationMapper INSTANCE = Mappers.getMapper(DeprecationMapper.class);

    Deprecation toDeprecation(DeprecationDTO deprecationDTO);

    DeprecationDTO toDeprecationDTO(Deprecation deprecation);
}
