package com.opentech.pharmacy.application.repository;

import com.opentech.pharmacy.application.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, String> {
    Optional<Pharmacy> findByNomContains(String nom);
}
