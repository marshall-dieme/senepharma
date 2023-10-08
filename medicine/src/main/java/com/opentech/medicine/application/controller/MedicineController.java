package com.opentech.medicine.application.controller;

import com.opentech.medicine.application.model.Medicine;
import com.opentech.medicine.application.service.FormPhService;
import com.opentech.medicine.application.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/medicines")
public record MedicineController(MedicineService medicineService, FormPhService formPhService) {
    private static final String className = MedicineController.class.getSimpleName();

    @GetMapping("/all")
    public ResponseEntity<Page<Medicine>> getAll(@RequestParam(defaultValue = "0") int page) {
        log.info("Entrée dans la methode 'getAll' de la classe {}", className);
        Page<Medicine> medicines = null;
        try {
            medicines = medicineService.getAll(page);
        }catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }

        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Medicine>> getByNom(@RequestParam String nom, @RequestParam(defaultValue = "0") int page) {
        log.info("Entrée de la methode 'getByNom' de la classe {}", className);
        log.info("Récuperation des informations concernant {}", nom);
        Page<Medicine> medicines = null;
        try {
            medicines = medicineService.getByName(nom, page);
        }catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }

        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Optional<Medicine>> getById(@PathVariable String id) {
        log.info("Entrée de la methode 'getById' de la classe {}", className);
        log.info("Récuperation des informations concernant {}", id);
        Optional<Medicine> medicine = null;
        try {
            medicine = medicineService.getById(id);
            if (medicine.isEmpty()) {
                log.info("Impossible de récuperer l'objet avec la valeur : {}", id);
            }
        }catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }

        return new ResponseEntity<>(medicine, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Medicine> create(@RequestBody Medicine medicine) {
        log.info("Entrée dans la methode 'create' de la classe {}", className);

        Medicine medicine1 = null;

        try {
            medicine1 = medicineService.create(medicine);
            if (medicine1 == null || medicine1.getId().isEmpty())
                log.error("Impossible de traiter la demande...");
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }

        log.info("Sortie de la methode 'create' de la classe {}", className);

        return new ResponseEntity<>(medicine1, HttpStatus.CREATED);
    }
}
