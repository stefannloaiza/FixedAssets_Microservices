package com.perficient.fixedassets.usersmicroservice.application.usecase;

import com.perficient.fixedassets.usersmicroservice.domain.models.dto.UserDTO;
import com.perficient.fixedassets.usersmicroservice.domain.models.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserUseCase {

    Iterable<UserDTO> getAllUsers();

    Iterable<UserDTO> getUsersByActive(boolean active);

    UserDTO getUserById(Long id);

    ResponseEntity<UserResponse> createUser(UserDTO userDTO);

    ResponseEntity<UserResponse> updateUser(Long id, UserDTO userDTO);
}