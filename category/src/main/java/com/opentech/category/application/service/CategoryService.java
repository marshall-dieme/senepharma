package com.opentech.category.application.service;

import com.opentech.category.application.model.Category;
import com.opentech.category.application.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class CategoryService {

    private static final String className = CategoryService.class.getSimpleName();

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        log.info("Entrée dans la methode 'getAll' de la classe {}", className);
        List<Category> categories = new ArrayList<>();

        try {
            categories = categoryRepository.findAll();
            if (categories.isEmpty())
                log.info("Aucune entrée dans la base de données");
        }catch (Exception e) {
            log.error("Une erreur est survenue lor de l'execution de la demande");
        }

        log.info("Sortie de la methode 'getAll' de la classe {}", className);
        return categories;
    }

    public Category findOneById(String id) {
        log.info("Entrée dans la methode 'findOneById' de la classe {} avec le paramètre '{}'", className, id);
        Category category = null;
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(id);

            if (optionalCategory.isPresent()) {
                category = optionalCategory.get();
            }else {
                log.info("L'objet demandé n'existe pas dans la base de données");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'findOneById' de la classe {}", className);
        return category;
    }

    public Category findOneByNom(String nom) {
        log.info("Entrée dans la methode 'findOneById' de la classe {} avec le paramètre '{}'", className, nom);
        Category category = null;
        try {
            Optional<Category> optionalCategory = categoryRepository.findByNom(nom);

            if (optionalCategory.isPresent()) {
                category = optionalCategory.get();
            }else {
                log.info("L'objet demandé n'existe pas dans la base de données");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'findOneById' de la classe {}", className);
        return category;
    }

    public Category create(Category category) {
        log.info("Entrée dans la methode 'create' de la classe {}", className);
        log.info("{}", category);
        try {
            category.setDateCreation(LocalDate.now());
            category.setDateModification(LocalDate.now());
            category = categoryRepository.save(category);
            if (category.getId().toString().isEmpty()) {
                log.info("Impossible de traiter la demande");
                throw new Exception();
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement des données");
        }
        return category;
    }

    public Category update(String id, Category category) {
        log.info("Entrée dans la methode 'create' de la classe {}", className);
        log.info("{}", category);
        try {
            Category category1 = findOneById(id);
            if (category1 != null) {
                category.setDateModification(LocalDate.now());
                category.setId(category1.getId());
                category = categoryRepository.save(category);
            } else {
                log.info("Impossible de traiter la demande");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement des données");
        }
        return category;
    }
}
