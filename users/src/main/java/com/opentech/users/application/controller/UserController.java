package com.opentech.users.application.controller;

import com.opentech.users.application.model.Utilisateur;
import com.opentech.users.application.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<Optional<Utilisateur>> getByUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
        log.info("Entrée dans la methode 'getByUsernameAndPassword' du controller 'UserController'");
        Optional<Utilisateur> user = Optional.empty();
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            user = service.findByUsernameAndPassword(username, password);
            if (user.isPresent())
                status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'getByUsernameAndPassword' du controller 'UserController'");

        return new ResponseEntity<>(user, status);
    }

    @GetMapping("/login")
    public ResponseEntity<Optional<Utilisateur>> getByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        log.info("Entrée dans la methode 'getByEmailAndPassword' du controller 'UserController'");
        Optional<Utilisateur> user = Optional.empty();
        HttpStatus status = HttpStatus.NOT_FOUND;
        try {
            user = service.findByEmailAndPassword(email, password);
            if (user.isPresent())
                status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'getByEmailAndPassword' du controller 'UserController'");

        return new ResponseEntity<>(user, status);
    }

    @PostMapping
    public ResponseEntity<Utilisateur> createUser(@RequestBody @Validated Utilisateur user) {
        log.info("Entrée dans la methode 'createUser' du controller 'UserController'");
        HttpStatus status = HttpStatus.NOT_MODIFIED;
        try {
            service.createUser(user);
            if (!user.getId().isEmpty())
                status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Une erreur est survenue lors du traitement de la demande");
        }
        log.info("Sortie de la methode 'createUser' du controller 'UserController'");

        return new ResponseEntity<>(user, status);
    }

}
