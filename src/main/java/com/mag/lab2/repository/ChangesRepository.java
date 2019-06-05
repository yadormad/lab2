package com.mag.lab2.repository;

import com.mag.lab2.model.entity.ChangesTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangesRepository extends JpaRepository<ChangesTableEntity, Long> {
}
