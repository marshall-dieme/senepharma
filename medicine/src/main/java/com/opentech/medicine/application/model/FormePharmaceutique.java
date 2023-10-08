package com.opentech.medicine.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FormePharmaceutique {
    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
    private String nom;
}

