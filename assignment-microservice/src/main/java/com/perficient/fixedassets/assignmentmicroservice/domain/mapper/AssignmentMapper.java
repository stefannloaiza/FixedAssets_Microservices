package com.perficient.fixedassets.assignmentmicroservice.domain.mapper;

import com.perficient.fixedassets.assignmentmicroservice.domain.entity.Assignments;
import com.perficient.fixedassets.assignmentmicroservice.domain.models.dto.AssignmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AssignmentMapper {

    AssignmentMapper INSTANCE = Mappers.getMapper(AssignmentMapper.class);

    Assignments mapToAssignment(AssignmentDTO assignmentDTO);

    AssignmentDTO mapToAssignmentDTO(Assignments assignments);

}
