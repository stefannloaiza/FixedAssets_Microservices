package com.perficient.fixedassets.depreciationmicroservice.domain.entity;

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

@Entity
@Table(name = "deprecation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deprecation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long assetId;
    private Integer year;
    private Double depreciationRate;
    private Double depreciationValue;
    private String depreciationType;
    private Double actualValue;
    private Double residualValue;
    private Integer estimatedLifeTime;
}