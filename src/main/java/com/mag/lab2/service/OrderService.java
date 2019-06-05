package com.mag.lab2.service;

import com.mag.lab2.model.dto.Order;
import com.mag.lab2.model.dto.OrderStatus;
import com.mag.lab2.service.exception.DateOrderException;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order) throws DateOrderException;
    void delete(long id);
    Order editOrder(Order order) throws DateOrderException;
    List<Order> getAll();
    List<OrderStatus> getAllStatuses();
    Order getOrderById(long id);
}
