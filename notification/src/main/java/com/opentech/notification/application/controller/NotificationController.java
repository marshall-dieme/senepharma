package com.opentech.notification.application.controller;

import com.opentech.notification.application.model.Notification;
import com.opentech.notification.application.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@Slf4j
@AllArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @GetMapping("{userId}")
    public ResponseEntity<List<Notification>> getByUser(@PathVariable String userId) {
        log.info("Entrée dans la methode 'getByUser' du controller 'NotificationController'");
        log.info("Parametre userId : {}", userId);
        List<Notification> notifications = new ArrayList<>();
        try {
            notifications = service.getByUser(userId);

            if (notifications.isEmpty())
                log.info("Aucune donnée récupérée");
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors du traitement de la demande");
        }

        log.info("Sortie la methode 'getByUser' du controller 'NotificationController'");
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNotification(@RequestBody Notification notification) {
        log.info("Entrée dans la methode 'createNotification' du controller 'NotificationController'");

        try {
            service.createNotification(notification);
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors du traitement de la demande");
        }

        log.info("Sortie de la methode 'createNotification' du controller 'NotificationController'");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> readNotifications(@PathVariable String userId) {
        log.info("Entrée dans la methode 'readNotifications' du controller 'NotificationController'");

        try {
            service.readNotification(userId);
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors du traitement de la demande");
        }

        log.info("Sortie de la methode 'readNotifications' du controller 'NotificationController'");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification( @PathVariable String id) {
        log.info("Entrée dans la methode 'readNotifications' du controller 'NotificationController'");

        try {
            service.deleteNotif(id);
        } catch (Exception e) {
            log.error("Une erreur s'est produite lors du traitement de la demande");
        }

        log.info("Sortie de la methode 'readNotifications' du controller 'NotificationController'");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
