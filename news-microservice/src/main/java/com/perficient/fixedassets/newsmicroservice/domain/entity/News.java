package com.perficient.fixedassets.newsmicroservice.domain.entity;

import com.perficient.fixedassets.newsmicroservice.domain.models.enums.Status;
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
    private Long assetId;
    private String userNotifier;
    private LocalDate registrationDate;
    private String title;
    private String description;
    private Status status;
    private String userAssigned;

    @Override
    public String toString() {
        return "News{id=%d, assetId=%d, userNotifier='%s', registrationDate=%s, title='%s', description='%s', status=%s, userAssigned='%s'}"
                .formatted(id, assetId, userNotifier, registrationDate, title, description, status, userAssigned);
    }
}
