package com.opentech.category.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Category {
    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
    private String nom;

    @Column(columnDefinition = "text")
    private String description;

    private LocalDate dateCreation;

    private LocalDate dateModification;
}
