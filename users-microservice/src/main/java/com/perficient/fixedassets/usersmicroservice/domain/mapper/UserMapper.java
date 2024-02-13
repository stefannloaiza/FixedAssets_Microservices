package com.perficient.fixedassets.usersmicroservice.domain.mapper;

import com.perficient.fixedassets.usersmicroservice.domain.entity.User;
import com.perficient.fixedassets.usersmicroservice.domain.models.dto.UserDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDTOToUser(UserDTO userDTO);

    UserDTO userToUserDTO(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromUserDTO(UserDTO userDTO, @MappingTarget User user);
}