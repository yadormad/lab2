package com.mag.lab2.repository;

import com.mag.lab2.model.entity.LoggerTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepository extends JpaRepository<LoggerTableEntity, Long> {
}
