package com.mag.lab2.repository;

import com.mag.lab2.model.entity.EntityTypeTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityTypeRepository extends JpaRepository<EntityTypeTableEntity, Long> {
}
