package com.perficient.fixedassets.assignmentmicroservice.domain.repository;

import com.perficient.fixedassets.assignmentmicroservice.domain.entity.Assignments;

import java.util.List;

public interface AssignmentRepository {

    Assignments save(Assignments assignments);

    List<Assignments> findAll();
}