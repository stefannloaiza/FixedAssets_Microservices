package com.perficient.fixedassets.usersmicroservice.domain.repository;

import com.perficient.fixedassets.usersmicroservice.domain.entity.User;

import java.util.Collection;

public interface UserRepository {

    User save(User user);

    User findById(Long id);

    Collection<User> findAll();

    Collection<User> findByActive(Boolean active);

    void deleteById(Long id);
}
