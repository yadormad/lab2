package com.mag.lab2.repository;

import com.mag.lab2.model.entity.MachinistTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachinistRepository extends JpaRepository<MachinistTableEntity, Long> {
}
