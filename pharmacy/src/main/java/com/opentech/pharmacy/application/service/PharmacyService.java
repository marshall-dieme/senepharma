package com.opentech.pharmacy.application.service;

import com.opentech.pharmacy.application.model.Pharmacy;
import com.opentech.pharmacy.application.repository.PharmacyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class PharmacyService {


    private static final String className = PharmacyService.class.getSimpleName();

    private final PharmacyRepository repository;

    public PharmacyService(PharmacyRepository repository) {
        this.repository = repository;
    }

    public Pharmacy create(Pharmacy p) {
        log.info("Entrée dans le controller {} dans la methode 'create' ", className);
        Pharmacy pharmacy = new Pharmacy();
        try{
            pharmacy = repository.save(p);
        }catch (Exception e) {
            log.error("Erreur lors de la création de l'objet dans la base de données : {}", e.getMessage());
        }

        if (pharmacy.getUuid() == null) {
            log.error("Une erreur s'est produite lors de la creation de l'objet");
        }

        return pharmacy;
    }

    public Pharmacy update(String id, Pharmacy p) {
        log.info("Entrée dans le controller {} dans la methode 'update' ", className);
        Pharmacy updatedPharmacy = null;
        Optional<Pharmacy> pharmacy = Optional.empty();
        try{
            pharmacy = repository.findById(id);
            pharmacy.ifPresent(value -> p.setUuid(value.getUuid()));

            updatedPharmacy = repository.save(p);
        }catch (EntityNotFoundException exception) {
            log.error("L'objet recherché n'existe pas dans la base de donnée");
        }

        if (pharmacy.isPresent() && (updatedPharmacy == null || !updatedPharmacy.getUuid().equals(pharmacy.get().getUuid()))){
                log.error("Impossible d'effectuer la mise à jour de l'objet");
                updatedPharmacy = new Pharmacy();
        }

        return updatedPharmacy;
    }

    public Page<Pharmacy> getAll(int p) {
        log.info("Entrée dans le controller {} dans la methode 'getAll' ", className);
        Pageable pageable = PageRequest.of(p, 20);
        Page<Pharmacy> page = null;
        try {
            page = repository.findAll(pageable);

            if (page.isEmpty())
                throw new EntityNotFoundException();
        } catch (Exception e) {
            log.error("Aucun Objet trouvé...");
        }

        return page;
    }

    public Pharmacy getById(String uuid) {
        log.info("Entrée dans le controller {} dans la methode 'getById' avec le paramètre : {} ", className, uuid);

        Pharmacy pharmacy = new Pharmacy();

        try {
            Optional<Pharmacy> optional = repository.findById(uuid);

            if (optional.isPresent()){
                pharmacy = optional.get();
            }else {
                log.error("Aucune donnée trouvé avec la valeur : {}", uuid);
            }
        } catch (Exception e) {
            log.error("Aucune donnée trouvé");
        }

        return pharmacy;
    }

    public Pharmacy getByNom(String nom) {
        log.info("Entrée dans le controller {} dans la methode 'getByNom' avec le paramètre : {} ", className, nom);

        Pharmacy pharmacy = new Pharmacy();

        try {
            Optional<Pharmacy> optional = repository.findByNomContains(nom);

            if (optional.isPresent()){
                pharmacy = optional.get();
            }else {
                log.error("Aucune donnée trouvé avec la valeur : {}", nom);
            }
        } catch (Exception e) {
            log.error("Aucune donnée trouvé");
        }

        return pharmacy;
    }
}
