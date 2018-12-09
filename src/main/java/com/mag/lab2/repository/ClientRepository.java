package com.mag.lab2.repository;

import com.mag.lab2.model.entity.ClientTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientTableEntity, Long> {
}
