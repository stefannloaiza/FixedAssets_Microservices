package com.perficient.fixedassets.usersmicroservice.infrastructure.jpa;

import com.perficient.fixedassets.usersmicroservice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    Collection<User> findByActive(Boolean active);
}

