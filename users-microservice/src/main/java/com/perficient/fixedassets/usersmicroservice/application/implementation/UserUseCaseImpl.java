package com.perficient.fixedassets.usersmicroservice.application.implementation;

import com.perficient.fixedassets.usersmicroservice.application.usecase.UserUseCase;
import com.perficient.fixedassets.usersmicroservice.domain.entity.User;
import com.perficient.fixedassets.usersmicroservice.domain.mapper.UserMapper;
import com.perficient.fixedassets.usersmicroservice.domain.models.dto.UserDTO;
import com.perficient.fixedassets.usersmicroservice.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return userRepository.findById(id)
                .map(UserMapper.INSTANCE::userToUserDTO)
                .orElse(null);
    }

    @Override
    public void createUser(UserDTO userDTO) {
        userRepository.save(UserMapper.INSTANCE.userDTOToUser(userDTO));
        log.info("User created: {}", userDTO);
    }

    @Override
    public Boolean updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElse(null);
        if (Objects.isNull(user)) {
            log.error("User not found: {}", id);
            return false;
        }
        userRepository.save(UserMapper.INSTANCE.updateUserFromUserDTO(userDTO, user));
        log.info("User updated: {}", user);
        return true;
    }
}
