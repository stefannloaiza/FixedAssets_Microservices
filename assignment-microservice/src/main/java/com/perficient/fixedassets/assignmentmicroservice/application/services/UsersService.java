package com.perficient.fixedassets.assignmentmicroservice.application.services;

import com.perficient.fixedassets.assignmentmicroservice.domain.models.dto.UserDTO;

public interface UsersService {
    UserDTO getUserByUserId(Long userId);
}
