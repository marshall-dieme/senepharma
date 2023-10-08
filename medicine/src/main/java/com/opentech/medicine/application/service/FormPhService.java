package com.opentech.medicine.application.service;


import com.opentech.medicine.application.model.FormePharmaceutique;
import com.opentech.medicine.application.model.Medicine;
import com.opentech.medicine.application.repository.FormePhRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public record FormPhService(FormePhRepository repository) {

    private static final String className = FormPhService.class.getSimpleName();
    public List<FormePharmaceutique> getAll(){
        log.info("Entrée dans la methode 'getAll' de la classe {} ", className);
        List<FormePharmaceutique> pageData = null;

        try {
            pageData = repository.findAll();
            if (pageData.isEmpty()){
                log.info("Aucune donnée récupérée...");
            }
        }catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'getAll' de la classe {}", className);

        return pageData;
    }

    public List<FormePharmaceutique> create(List<FormePharmaceutique> list){
        log.info("Entrée dans la methode 'create' de la classe {} ", className);
        List<FormePharmaceutique> pageData = null;

        try {
            pageData = repository.saveAll(list);
            if (pageData.isEmpty()){
                log.info("Aucune donnée enregister...");
            }
        }catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'create' de la classe {}", className);

        return pageData;
    }
}
