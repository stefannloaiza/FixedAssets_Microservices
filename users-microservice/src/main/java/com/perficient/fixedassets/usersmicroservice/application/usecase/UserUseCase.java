package com.perficient.fixedassets.usersmicroservice.application.usecase;

import com.perficient.fixedassets.usersmicroservice.domain.models.dto.UserDTO;

public interface UserUseCase {

    Iterable<UserDTO> getAllUsers();

    Iterable<UserDTO> getUsersByActive(boolean active);

    UserDTO getUserById(Long id);

    void createUser(UserDTO userDTO);

    Boolean updateUser(Long id, UserDTO userDTO);
}