package com.opentech.users.application.service;

import com.opentech.users.application.model.Utilisateur;
import com.opentech.users.application.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.NullArgumentException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository repository;

    public Optional<Utilisateur> findByUsernameAndPassword(String username, String password) {
        log.info("Entrée dans la methode 'findByUsernameAndPassword' du service 'UserService'");
        Optional<Utilisateur> user = Optional.empty();
        try {
            if (username.isEmpty() || password.isEmpty())
                throw new NullArgumentException("");

            user = repository.findByUsernameAndPassword(username, password);

            if (user.isEmpty()) {
                log.info("Aucune donnée correspondante...");
                throw new EntityNotFoundException();
            }
        } catch (Exception e) {
            log.error("Une erreur est survenu lors du traitement de la demande");
        }

        log.info("Sortie de la methode 'findByUsernameAndPassword' du service 'UserService'");
        return user;
    }

    public Optional<Utilisateur> findByEmailAndPassword(String email, String password) {
        log.info("Entrée dans la methode 'findByEmailAndPassword' du service 'UserService'");
        Optional<Utilisateur> user = Optional.empty();
        try {
            if (email.isEmpty() || password.isEmpty())
                throw new NullArgumentException("");

            user = repository.findByEmailAndPassword(email, password);

            if (user.isEmpty()) {
                log.info("Aucune donnée correspondante...");
                throw new EntityNotFoundException();
            }
        } catch (Exception e) {
            log.error("Une erreur est survenu lors du traitement de la demande");
        }

        log.info("Sortie de la methode 'findByEmailAndPassword' du service 'UserService'");
        return user;
    }

    public Utilisateur createUser(Utilisateur user) {
        log.info("Entrée dans la methode 'createUser' du service 'UserService'");
        try {
            repository.saveAndFlush(user);

            if (user.getId().isEmpty()) {
                throw new Exception("Impossible de terminer la demande");
            }
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");

        }
        log.info("Sortie de  la methode 'createUser' du service 'UserService'");
        return user;
    }
}
