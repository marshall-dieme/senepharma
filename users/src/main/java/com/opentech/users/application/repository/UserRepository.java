package com.opentech.users.application.repository;

import com.opentech.users.application.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Utilisateur, String> {
    Optional<Utilisateur> findByUsernameAndPassword(String username, String password);

    Optional<Utilisateur> findByEmailAndPassword(String email, String password);
}
