package com.opentech.category.application.controller;

import com.opentech.category.application.model.Category;
import com.opentech.category.application.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("api/v1/categories")
public record CategoryController(CategoryService categoryService) {
    private static final String className = CategoryController.class.getSimpleName();

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        log.info("Entrée dans la methode 'getAll' dle la classe {}", className);
        List<Category> categories = new ArrayList<>();

        try{
            categories = categoryService.getAll();
            if (categories.isEmpty()) {
                log.info("Aucune donnée récuperée...");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }

        log.info("Sortie de la methode 'getAll' de la classe {}", className);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable String id) {
        log.info("Entrée dans la methode 'getById' dle la classe {} avec le paramètre {}", className, id);
        Category category = null;

        try{
            category = categoryService.findOneById(id);
            if (category == null) {
                log.info("Aucune donnée récuperée...");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }

        log.info("Sortie de la methode 'getById' de la classe {}", className);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{nom}")
    public ResponseEntity<Category> getByNom(@PathVariable String nom) {
        log.info("Entrée dans la methode 'getByNom' dle la classe {} avec le paramètre {}", className, nom);
        Category category = null;

        try{
            category = categoryService.findOneByNom(nom);
            if (category == null) {
                log.info("Aucune donnée récuperée...");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }

        log.info("Sortie de la methode 'getByNom' de la classe {}", className);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        log.info("Entrée dans la methode 'create' dle la classe {}", className);
        log.info("{}", category);
        Category category1 = null;

        try {
            category1 = categoryService.create(category);

            if (category1 == null) {
                log.info("Aucun enregistrement n'a été effectué");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement des données");
        }

        log.info("Sortie de la methode 'create' de la classe {}", className);
        return new ResponseEntity<>(category1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable String id, @RequestBody Category category) {
        log.info("Entrée dans la methode 'update' dle la classe {}", className);
        log.info("{}", category);
        Category category1 = null;

        try {
            category1 = categoryService.update(id, category);

            if (category1 == null) {
                log.info("Aucun enregistrement n'a été effectué");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement des données");
        }

        log.info("Sortie de la methode 'update' de la classe {}", className);
        return new ResponseEntity<>(category1, HttpStatus.CREATED);
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<Category>> createMultiple(@RequestBody List<Category> categories) {
        List<Category> created = new ArrayList<>();
        if (!categories.isEmpty()) {
            for (Category c :
                    categories) {
                created.add(categoryService.create(c));
            }
        }

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
