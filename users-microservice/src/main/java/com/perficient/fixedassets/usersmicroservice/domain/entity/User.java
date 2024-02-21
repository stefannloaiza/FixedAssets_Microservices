package com.perficient.fixedassets.usersmicroservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Integer phone;
    private LocalDate lastLogin;
    private LocalDate registrationDate;
    private Boolean active;
    private Boolean locked;
    private String role;

    @Override
    public String toString() {
        return "User{id=%d, name='%s', username='%s', email='%s', password='%s', phone=%d, lastLogin=%s, registrationDate=%s, active=%s, locked=%s, role='%s'}"
                .formatted(id, name, username, email, password, phone, lastLogin, registrationDate, active, locked, role);
    }
}
