package com.perficient.fixedassets.usersmicroservice.domain.models.dto;

import java.time.LocalDate;

public record UserDTO(
        Long id,
        String name,
        String username,
        String email,
        String password,
        Integer phone,
        LocalDate lastLogin,
        LocalDate registrationDate,
        Boolean active,
        Boolean locked,
        String role
) {
}
