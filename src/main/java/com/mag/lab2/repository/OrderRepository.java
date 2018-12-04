package com.mag.lab2.repository;

import com.mag.lab2.entity.OrderTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderTableEntity, Long> {
}
