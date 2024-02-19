package com.perficient.fixedassets.newsmicroservice.domain.entity;

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
@Table(name = "news")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long assetId;
    private LocalDate registrationDate;
    private String title;
    private String description;

    @Override
    public String toString() {
        return "News{id=%d, assetId=%d, registrationDate=%s, title='%s', description='%s'}"
                .formatted(id, assetId, registrationDate, title, description);
    }
}
