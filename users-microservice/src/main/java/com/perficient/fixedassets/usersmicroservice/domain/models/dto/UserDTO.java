package com.perficient.fixedassets.usersmicroservice.domain.models.dto;

public record UserDTO(
        Long id,
        String name,
        String email,
        String password,
        Boolean active,
        Boolean locked,
        String role
) {
}
