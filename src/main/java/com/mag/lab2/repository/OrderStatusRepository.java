package com.mag.lab2.repository;

import com.mag.lab2.model.entity.OrderStatusTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatusTableEntity, Long> {
}
