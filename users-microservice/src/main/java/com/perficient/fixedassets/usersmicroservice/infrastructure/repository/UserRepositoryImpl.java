package com.perficient.fixedassets.usersmicroservice.infrastructure.repository;

import com.perficient.fixedassets.usersmicroservice.domain.entity.User;
import com.perficient.fixedassets.usersmicroservice.domain.repository.UserRepository;
import com.perficient.fixedassets.usersmicroservice.infrastructure.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userJpaRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public Collection<User> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public Collection<User> findByActive(Boolean active) {
        return userJpaRepository.findByActive(active);
    }

    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }
}
