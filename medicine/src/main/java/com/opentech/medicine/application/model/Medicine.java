package com.opentech.medicine.application.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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

    @Column(columnDefinition = "text")
    private String prescription;

    private String category;
}
