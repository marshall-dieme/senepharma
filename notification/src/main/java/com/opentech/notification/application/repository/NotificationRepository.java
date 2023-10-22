package com.opentech.notification.application.repository;

import com.opentech.notification.application.model.Notification;
import com.opentech.notification.application.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
    List<Notification> findByUserIdAndStatusIsNotOrderByDateDesc(String userId, Status status);
}
