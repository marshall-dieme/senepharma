package com.opentech.notification.application.service;

import com.opentech.notification.application.model.Notification;
import com.opentech.notification.application.model.Status;
import com.opentech.notification.application.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    public List<Notification> getByUser(String userId) {
        log.info("Entrée dans la methode 'getByUser' du service 'NotificationService'");
        List<Notification> notifications = new ArrayList<>();

        try {
            notifications = repository.findByUserIdAndStatusIsNotOrderByDateDesc(userId, Status.DELETED);
            if (notifications.isEmpty())
                log.info("Aucune donnée enregistré");

        } catch (Exception e) {
            log.error("Une erreur s'est produite lors du traitement de la demande");
        }

        log.info("Sortie la methode 'getByUser' du service 'NotificationService'");

        return notifications;
    }

    public void createNotification(Notification notification) {
        log.info("Entrée dans la methode 'createNotification' du service 'NotificationService'");
        Notification notification1 = null;
        try {
            notification1 = repository.save(notification);

            if (notification1.getId() == null) {
                log.info("Impossible de créer l'objet");
            }

        } catch (Exception e) {
            log.error("Une erreur s'est produite lors du traitement de la demande");
        }

        log.info("Sortie la methode 'createNotification' du service 'NotificationService'");
    }


}
