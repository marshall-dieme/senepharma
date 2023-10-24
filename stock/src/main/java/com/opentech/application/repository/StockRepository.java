package com.opentech.application.repository;

import com.opentech.application.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

    List<Stock> findByPharmacyId(String pharmacyId);

    Optional<Stock> findByMedicineId(String medicineId, String id);

    Optional<Stock> findByMedicineIdAndPharmacyId(String medicineId, String pharmacyId);
}
