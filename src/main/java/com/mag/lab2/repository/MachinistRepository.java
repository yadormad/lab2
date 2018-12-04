package com.mag.lab2.repository;

import com.mag.lab2.entity.MachinistTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachinistRepository extends JpaRepository<MachinistTableEntity, Long> {
}
