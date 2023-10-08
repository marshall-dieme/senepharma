package com.opentech.category.application.repository;

import com.opentech.category.application.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByNom(String nom);
}
