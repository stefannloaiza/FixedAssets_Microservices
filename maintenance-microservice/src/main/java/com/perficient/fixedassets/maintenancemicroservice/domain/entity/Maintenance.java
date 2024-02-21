package com.perficient.fixedassets.maintenancemicroservice.domain.entity;

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
@Table(name = "maintenance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long assetId;
    private String description;
    private LocalDate maintenanceDate;
    private String status;
    private LocalDate finalizationDate;

    @Override
    public String toString() {
        return "Maintenance{id=%d, assetId=%d, description='%s', maintenanceDate=%s, status='%s', finalizationDate=%s}"
                .formatted(id, assetId, description, maintenanceDate, status, finalizationDate);
    }
}
