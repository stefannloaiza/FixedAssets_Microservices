package com.perficient.fixedassets.assetsmicroservice.domain.entity;

import com.perficient.fixedassets.assetsmicroservice.domain.models.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "asset")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String code;

    @Enumerated(value = EnumType.STRING)
    private Status status;
    private LocalDate acquisitionDate;
    private Double acquisitionCost;
    private Status assignmentStatus;

    @Override
    public String toString() {
        return "Asset{id=%d, name='%s', description='%s', code='%s', status=%s, acquisitionDate=%s, acquisitionCost=%s, assignmentStatus=%s}"
                .formatted(id, name, description, code, status, acquisitionDate, acquisitionCost, assignmentStatus);
    }
}
