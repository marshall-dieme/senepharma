package com.opentech.pharmacy.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pharmacy {
    @EqualsAndHashCode.Include
    @Id
    @UuidGenerator
    private String uuid;

    private String nom;

    @Column(columnDefinition = "text")
    private String adresse;

    private String zipCode;

    private String ville;

    private String region;

    @Column(unique = true)
    private String telephone;

    @Column(unique = true)
    private String email;

    private LocalDate dateCreation;

    private Double latitude;

    private Double longitude;
}
