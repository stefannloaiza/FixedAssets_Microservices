package com.perficient.fixedassets.usersmicroservice.infrastructure.controller;

import com.perficient.fixedassets.usersmicroservice.application.usecase.UserUseCase;
import com.perficient.fixedassets.usersmicroservice.domain.models.dto.UserDTO;
import com.perficient.fixedassets.usersmicroservice.domain.models.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<UserDTO> getAllUsers() {
        return userUseCase.getAllUsers();
    }

    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<UserDTO> getUsersByActive(boolean active) {
        return userUseCase.getUsersByActive(active);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userUseCase.getUserById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDTO userDTO) {
        return userUseCase.createUser(userDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userUseCase.updateUser(id, userDTO);
    }
}