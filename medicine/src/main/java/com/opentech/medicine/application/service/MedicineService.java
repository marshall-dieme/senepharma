package com.opentech.medicine.application.service;

import com.opentech.medicine.application.model.Medicine;
import com.opentech.medicine.application.repository.MedicineRepository;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class MedicineService {

    private static final String className = MedicineService.class.getSimpleName();

    private final MedicineRepository repository;

    public MedicineService(MedicineRepository repository) {
        this.repository = repository;
    }

    public Page<Medicine> getAll(int page){
        log.info("Entrée dans la methode 'getAll' de la classe {} page : {}", className, page);
        Pageable pageable = PageRequest.of(page, 20);
        Page<Medicine> pageData = null;

        try {
            pageData = repository.findAll(pageable);
            if (!pageData.hasContent()){
                log.info("Aucune donnée récupérée...");
            }
        }catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'getAll' de la classe {}", className);

        return pageData;
    }

    public Optional<Medicine> getById(String id) {
        log.info("Entrée dans la methode 'getById' de la classe {} pour le medicament : {}", className, id);
        Optional<Medicine> medicine = null;
        try {
            medicine = repository.findById(id);

            if (medicine.isEmpty()) {
                log.error("Aucune valeur trouvé pour l'id {}", id);
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'getById' de la classe {}", className);
        return medicine;
    }

    public Page<Medicine> getByName(String nom, int page) {
        log.info("Entrée dans la methode 'getByName' de la classe {} pour le medicament : {}", className, nom);
        Page<Medicine> medicines = null;
        Pageable pageable = PageRequest.of(page, 20);
        try {
            medicines = repository.findByNom(nom, pageable);
            if (!medicines.hasContent()){
                log.info("Aucune donnée récupérée...");
            }
        }catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'getByName' de la classe {}", className);

        return medicines;
    }

    public Medicine create(Medicine medicine) {
        log.info("Entrée dans la methode 'getAll' de la classe {} pour le medicament : {}", className, medicine);
        try {
            medicine = repository.save(medicine);

            if (medicine.getId().isEmpty()) {
                log.error("Echec de l'enregistrement des données");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'create' de la classe {}", className);
        return medicine.getId().isEmpty() ? null : medicine;
    }

    public Medicine update(String id, Medicine medicine) {
        log.info("Entrée dans la methode 'getAll' de la classe {} pour le medicament : {}", className, medicine);
        try {
            Medicine m = getById(id).orElse(null);
            if (m != null) {
                medicine.setId(m.getId());
                medicine = repository.save(medicine);
            }
            else {
                log.error("Echec de l'enregistrement des données");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'update' de la classe {}", className);
        return medicine.getId().isEmpty() ? null : medicine;
    }
}
