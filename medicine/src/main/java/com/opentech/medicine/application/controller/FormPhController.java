package com.opentech.medicine.application.controller;

import com.opentech.medicine.application.model.FormePharmaceutique;
import com.opentech.medicine.application.service.FormPhService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/formes_pharmaceutiques")
public record FormPhController(FormPhService service) {

    @GetMapping
    public ResponseEntity<List<FormePharmaceutique>> getFormes() {
        log.info("Entrée dans la methode 'getFormes' du controller FormPhController");
        List<FormePharmaceutique> forms = null;
        try {
            forms = service.getAll();

            if (forms.isEmpty())
                log.info("Aucune donnée récupérer...");
        }catch (Exception e) {
            log.error("Une erreur est survenu lors du traitement de la demande");
        }

        return new ResponseEntity<>(forms, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<FormePharmaceutique>> create(@RequestBody List<FormePharmaceutique> forms) {
        log.info("Entrée dans la methode 'create' du controller FormPhController");
        List<FormePharmaceutique> formes = new ArrayList<>();
        try {
            formes = service.create(forms);
            if (formes.isEmpty())
                log.info("Impossible de traiter la demande");
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }

        return new ResponseEntity<>(formes, HttpStatus.CREATED);
    }
}
