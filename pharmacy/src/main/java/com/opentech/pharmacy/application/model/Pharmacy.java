package com.opentech.pharmacy.application.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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
