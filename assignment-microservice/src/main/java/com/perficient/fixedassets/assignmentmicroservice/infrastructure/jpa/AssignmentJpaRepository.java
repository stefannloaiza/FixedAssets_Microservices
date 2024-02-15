package com.perficient.fixedassets.assignmentmicroservice.infrastructure.jpa;

import com.perficient.fixedassets.assignmentmicroservice.domain.entity.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentJpaRepository extends JpaRepository<Assignments, Long> {
}
