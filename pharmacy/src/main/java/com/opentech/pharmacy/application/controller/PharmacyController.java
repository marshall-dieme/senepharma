package com.opentech.pharmacy.application.controller;

import com.opentech.pharmacy.application.model.Pharmacy;
import com.opentech.pharmacy.application.service.PharmacyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/pharmacy")
@CrossOrigin
public record PharmacyController(PharmacyService pharmacyService) {

    private static final String className = PharmacyController.class.getSimpleName();

    @PostMapping
    public ResponseEntity<Pharmacy> create(@RequestBody Pharmacy pharmacy) {
        log.info("Entrée dans le controller {} dans la methode 'create' ", className);
        Pharmacy pharmacy1 = new Pharmacy();
        HttpStatus status;
        try {
            pharmacy1 = pharmacyService.create(pharmacy);
            status = HttpStatus.CREATED;
        }catch (Exception e) {
            log.error("Une erreur s'est produite lors de l'execution de la demande");
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(pharmacy1, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pharmacy> update(@PathVariable String id, @RequestBody Pharmacy pharmacy){
        log.info("Entrée dans le controller {} dans la methode 'update' ", className);
        Pharmacy pharmacy1 = new Pharmacy();
        HttpStatus status;
        try{
            pharmacy1 = pharmacyService.update(id, pharmacy);
            if (!pharmacy1.getUuid().isEmpty() && pharmacy1.getUuid().equals(id)) {
                status = HttpStatus.ACCEPTED;
            }else {
                throw new EntityNotFoundException();
            }
        } catch (Exception e) {
            log.error("Impossible d'effectuer la modification de l'objet {}", id);
            status = HttpStatus.NOT_MODIFIED;
        }
        return new ResponseEntity<>(pharmacy1, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy> findById(@PathVariable String id) {
        log.info("Entrée dans le controller {} dans la methode 'findById' : {}", className, id);
        Pharmacy pharmacy1 = new Pharmacy();
        HttpStatus status = HttpStatus.OK;
        try{
            pharmacy1 = pharmacyService.getById(id);
            if (pharmacy1.getUuid().isEmpty() && !pharmacy1.getUuid().equals(id)) {
                status = HttpStatus.NOT_FOUND;
                throw new EntityNotFoundException();
            }
        }catch (Exception e) {
            log.error("Aucune référence trouvée pour la valeur : {}" , id);
        }
        return new ResponseEntity<>(pharmacy1, status);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Pharmacy> findByNom(@PathVariable String name) {
        log.info("Entrée dans le controller {} dans la methode 'findByNom' : {}", className, name);
        Pharmacy pharmacy1 = new Pharmacy();
        HttpStatus status = HttpStatus.OK;
        try{
            pharmacy1 = pharmacyService.getByNom(name);
            if (pharmacy1.getUuid().isEmpty() && !pharmacy1.getUuid().equals(name)) {
                status = HttpStatus.NOT_FOUND;
                throw new EntityNotFoundException();
            }
        }catch (Exception e) {
            log.error("Aucune référence trouvée pour la valeur : {}" , name);
        }
        return new ResponseEntity<>(pharmacy1, status);
    }

    @GetMapping
    public ResponseEntity<Page<Pharmacy>> findAll(@RequestParam(defaultValue = "0") int page){
        log.info("Entrée dans le controller {} dans la methode 'findAll'", className);
        Page<Pharmacy> pharmacies = null;
        try{
            pharmacies = pharmacyService.getAll(page);
        }catch (Exception e) {
            log.error("Aucune donnée récuperé...");
        }
        return new ResponseEntity<>(pharmacies, HttpStatus.OK);
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<Pharmacy>> createMultiple(@RequestBody List<Pharmacy> pharmacies) {
        log.info("Entrée dans le controller {} dans la methode 'createMultiple'", className);
        List<Pharmacy> created = new ArrayList<>();

        if (pharmacies.size() > 0) {
            for (Pharmacy p :
                    pharmacies) {
                created.add(pharmacyService.create(p));
            }
        }

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
