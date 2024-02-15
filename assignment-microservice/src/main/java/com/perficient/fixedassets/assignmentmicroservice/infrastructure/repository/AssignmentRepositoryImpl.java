package com.perficient.fixedassets.assignmentmicroservice.infrastructure.repository;

import com.perficient.fixedassets.assignmentmicroservice.domain.entity.Assignments;
import com.perficient.fixedassets.assignmentmicroservice.domain.repository.AssignmentRepository;
import com.perficient.fixedassets.assignmentmicroservice.infrastructure.jpa.AssignmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AssignmentRepositoryImpl implements AssignmentRepository {

    private final AssignmentJpaRepository assignmentJpaRepository;

    @Override
    public Assignments save(Assignments assignments) {
        return assignmentJpaRepository.save(assignments);
    }

    @Override
    public List<Assignments> findAll() {
        return assignmentJpaRepository.findAll();
    }

}
