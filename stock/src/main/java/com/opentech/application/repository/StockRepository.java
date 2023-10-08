package com.opentech.application.repository;

import com.opentech.application.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

    Optional<Stock> findByPharmacyId(String pharmacyId);

    List<Stock> findByMedicineId(String medicineId);

    Optional<Stock> findByMedicineIdAndPharmacyId(String medicineId, String pharmacyId);
}
