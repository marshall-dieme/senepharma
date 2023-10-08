package com.opentech.medicine.application.repository;

import com.opentech.medicine.application.model.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {
    Page<Medicine> findByNom(String nom, Pageable pageable);
}
