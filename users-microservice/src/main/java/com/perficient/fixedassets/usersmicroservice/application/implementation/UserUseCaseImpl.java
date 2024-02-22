package com.perficient.fixedassets.usersmicroservice.application.implementation;

import com.perficient.fixedassets.usersmicroservice.application.usecase.UserUseCase;
import com.perficient.fixedassets.usersmicroservice.application.validations.UserValidation;
import com.perficient.fixedassets.usersmicroservice.domain.entity.User;
import com.perficient.fixedassets.usersmicroservice.domain.mapper.UserMapper;
import com.perficient.fixedassets.usersmicroservice.domain.models.dto.UserDTO;
import com.perficient.fixedassets.usersmicroservice.domain.models.response.ErrorResponse;
import com.perficient.fixedassets.usersmicroservice.domain.models.response.UserResponse;
import com.perficient.fixedassets.usersmicroservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserUseCaseImpl implements UserUseCase {

    private final UserRepository userRepository;

    @Override
    public Iterable<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .toList();
    }

    @Override
    public Iterable<UserDTO> getUsersByActive(boolean active) {
        return userRepository.findByActive(active).stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .toList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return UserMapper.INSTANCE.userToUserDTO(userRepository.findById(id));
    }

    @Override
    public ResponseEntity<UserResponse> createUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.userDTOToUser(userDTO);

        List<ErrorResponse> errorResponseList = checkUser(user);
        checkIfUsernameExists(user, errorResponseList);
        if (!errorResponseList.isEmpty()) {
            return ResponseEntity.badRequest().body(new UserResponse("User not created", errorResponseList));
        }

        userRepository.save(user);
        log.info("User created: {}", user);
        return ResponseEntity.ok(new UserResponse("User created successfully", null));
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id);
        if (Objects.isNull(user)) {
            log.error("User not found: {}", id);
            return ResponseEntity.badRequest().body(new UserResponse("User not found", null));
        }
        user = UserMapper.INSTANCE.updateUserFromUserDTO(userDTO, user);
        List<ErrorResponse> errorResponseList = checkUser(user);
        if (!errorResponseList.isEmpty()) {
            return ResponseEntity.badRequest().body(new UserResponse("User not updated", errorResponseList));
        }

        user = userRepository.save(user);
        log.info("User updated: {}", user);
        return ResponseEntity.ok(new UserResponse("User updated", null));
    }

    private static List<ErrorResponse> checkUser(User user) {
        return UserValidation.checkUser(user);
    }

    private void checkIfUsernameExists(User user, List<ErrorResponse> errorResponseList) {
        if (Objects.nonNull(userRepository.findByUsername(user.getUsername()))) {
            errorResponseList.add(new ErrorResponse("Username already exists", HttpStatus.BAD_REQUEST));
        }
    }
}
