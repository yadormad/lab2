package com.mag.lab2.service;

import com.mag.lab2.model.Order;
import com.mag.lab2.model.OrderStatus;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order);
    void delete(long id);
    Order editOrder(Order order);
    List<Order> getAll();
    List<OrderStatus> getAllStatuses();
}
