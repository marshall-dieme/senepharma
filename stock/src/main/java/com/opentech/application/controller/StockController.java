package com.opentech.application.controller;

import com.opentech.application.model.Stock;
import com.opentech.application.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/stocks")
@AllArgsConstructor
public class StockController {

    private final StockService service;

    @GetMapping("/pharmacy/{pharmacyId}")
    public ResponseEntity<List<Stock>> getByPharmacy(@PathVariable String pharmacyId) {
        log.info("Entrée dans la methode 'getByPharmacy' du controller 'StockController'");
        List<Stock> stocks = new ArrayList<>();

        try {
            stocks = service.getStockByPharmacy(pharmacyId);
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'getByPharmacy' du controller 'StockController'");

        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping("/pharmacy/{pharmacyId}/medicine/{medicineId}")
    public ResponseEntity<Stock> getByPharmacyAndMedicine(@PathVariable String pharmacyId, @PathVariable String medicineId) {
        log.info("Entrée dans la methode 'getByPharmacyAndMedicine' du controller 'StockController'");
        Stock stock = null;
        try {
            stock = service.getByMedicine(medicineId, pharmacyId).orElse(null);
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors du traitement de la demande");
        }
        log.info("Sortie la methode 'getByPharmacyAndMedicine' du controller 'StockController'");

        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Stock> create(@RequestBody Stock stock) {
        log.info("Entrée dans la methode 'create' du controller 'StockController'");
        Stock stock1 = null;
        try {
            stock1 = service.create(stock);
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'create' du controller 'StockController'");

        return new ResponseEntity<>(stock1, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Stock> update(@RequestBody Stock stock) {
        log.info("Entrée dans la methode 'update' du controller 'StockController'");
        Stock stock1 = null;
        try {
            stock1 = service.updateStock(stock);
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'update' du controller 'StockController'");

        return new ResponseEntity<>(stock1, HttpStatus.CREATED);
    }


}
