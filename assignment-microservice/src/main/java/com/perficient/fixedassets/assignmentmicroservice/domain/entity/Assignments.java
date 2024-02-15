package com.perficient.fixedassets.assignmentmicroservice.domain.entity;

import jakarta.persistence.Column;
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
@Table(name = "assignments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Assignments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long assetId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDate assignmentDate;

    @Override
    public String toString() {
        return "Assignments {id=%d, assetId=%d, userId=%d, assignmentDate=%s}"
                .formatted(id, assetId, userId, assignmentDate);
    }
}