package com.opentech.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    @Id
    @UuidGenerator
    private String id;

    private String pharmacyId;

    private String medicineId;

    private Long quantity;
}
