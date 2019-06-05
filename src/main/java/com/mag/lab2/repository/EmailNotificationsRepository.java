package com.mag.lab2.repository;

import com.mag.lab2.model.entity.EmailNotificationsTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailNotificationsRepository extends JpaRepository<EmailNotificationsTable, Long> {
}
