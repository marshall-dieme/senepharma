package com.opentech.medicine.application.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Medicine {

    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
    private String nom;

    @ManyToOne
    private FormePharmaceutique formePharmaceutique;

    private String dosage;

    @Column(columnDefinition = "text")
    private String indication;

    @Column(columnDefinition = "text")
    private String contreIndication;

    @Column(columnDefinition = "text")
    private String posologie;

    @Column(columnDefinition = "text")
    private String effetsSecondaires;

    @Column(columnDefinition = "text")
    private String precautions;

    private String stockage;

    private LocalDate dateExpiration;

    private String nomeroLot;

    private Double prix;

    @Column(columnDefinition = "text")
    private String prescription;

    private String category;
}
