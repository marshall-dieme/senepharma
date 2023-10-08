package com.opentech.medicine.application.repository;

import com.opentech.medicine.application.model.FormePharmaceutique;
import com.opentech.medicine.application.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormePhRepository extends JpaRepository<FormePharmaceutique, String> {
    FormePharmaceutique findByNom(String nom);
}
