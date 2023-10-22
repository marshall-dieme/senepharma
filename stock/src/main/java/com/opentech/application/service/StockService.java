package com.opentech.application.service;

import com.opentech.application.model.Stock;
import com.opentech.application.repository.StockRepository;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class StockService {
    private static final String className = StockService.class.getSimpleName();

    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    public Optional<Stock> getStockByPharmacy(String pharmacyId) {
        log.info("Entrée dans la methode 'getStockByPharmacy' du service {}", className);
        Optional<Stock> stock = Optional.empty();

        try {
            stock = repository.findByPharmacyId(pharmacyId);
            if (stock.isEmpty()) {
                log.info("Aucune donnée récuperer pour la valeur {}", pharmacyId);
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traintement de la damande");
        }

        log.info("Sortie de la methode 'getStockByPharmacy' du service {}", className);

        return stock;
    }

    public List<Stock> getByMedicine(String medicineId) {
        log.info("Entrée dans la methode 'getByMedicine' du service {}", className);

        List<Stock> stocks = new ArrayList<>();

        try {
            stocks = repository.findByMedicineId(medicineId);

            if (stocks.isEmpty()) {
                log.info("Aucune donnée trouvé pour la valeur {}", medicineId);
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }

        log.info("Sortie de la methode 'getByMedicine' du service {}", className);

        return stocks;
    }

    public Stock create(Stock stock) {
        log.info("Entrée dans la methode 'create' du service {}", className);

        Stock st = null;

        try {
            st = repository.save(stock);
            if (st.getId().isEmpty()) {
                log.info("Impossible de traiter la demande");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }

        log.info("Sortie de la methode 'create' du service {}", className);

        return st;
    }

    public Stock updateStock(Stock dto) {
        log.info("Entrée dans la methode 'updateStock' du service {}", className);
        Stock updated = null;
        try {
            Optional<Stock> optional = repository.findByMedicineIdAndPharmacyId(dto.getMedicineId(), dto.getPharmacyId());
            if (optional.isPresent()) {
                Stock stock = optional.get();
                stock.setQuantity(stock.getQuantity() + dto.getQuantity());

                updated = repository.save(stock);

                if (updated.getId().isEmpty()) {
                    log.info("Impossible d'enregistrer la nouvelle valeur");
                }
            }else {
                log.info("Impossible de traiter la demande");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie dans la methode 'updateStock' du service {}", className);

        return updated;
    }

    public List<Stock> createMulti(List<Stock> stocks) {
        log.info("Entrée dans la methode 'createMulti' du service {}", className);
        List<Stock> created = new ArrayList<>();
        try {
            for (Stock stock :
                    stocks) {
                created.add(create(stock));
            }

            if (created.isEmpty()) {
                log.info("Impossible de traiter la demande");
            }
        } catch (Exception e) {
            log.info("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie dans la methode 'createMulti' du service {}", className);

        return created;
    }
}
