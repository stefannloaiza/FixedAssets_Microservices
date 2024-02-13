package com.perficient.fixedassets.usersmicroservice.infrastructure.repository;

import com.perficient.fixedassets.usersmicroservice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Collection<User> findByActive(Boolean active);
}

