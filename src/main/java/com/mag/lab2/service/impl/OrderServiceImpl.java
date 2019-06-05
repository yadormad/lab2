package com.mag.lab2.service.impl;

import com.mag.lab2.model.entity.OrderStatusTableEntity;
import com.mag.lab2.model.entity.OrderTableEntity;
import com.mag.lab2.model.dto.Order;
import com.mag.lab2.model.dto.OrderStatus;
import com.mag.lab2.repository.ClientRepository;
import com.mag.lab2.repository.MachinistRepository;
import com.mag.lab2.repository.OrderRepository;
import com.mag.lab2.repository.OrderStatusRepository;
import com.mag.lab2.service.OrderService;
import com.mag.lab2.service.converter.Converter;
import com.mag.lab2.service.converter.impl.OrderJPAConverterImpl;
import com.mag.lab2.service.exception.DateOrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private Converter<OrderTableEntity, Order> orderConverter;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ClientRepository clientRepository, MachinistRepository machinistRepository, OrderStatusRepository orderStatusRepository) {
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.orderConverter = new OrderJPAConverterImpl(orderRepository, clientRepository, machinistRepository, orderStatusRepository);
    }

    @Override
    public Order addOrder(Order order) throws DateOrderException {
        if(order.getStartDate().after(order.getEndDate())) throw new DateOrderException("End date is before start date");
        OrderTableEntity orderEntity = orderConverter.toEntity(order);
        orderRepository.saveAndFlush(orderEntity);
        return orderConverter.toDto(orderEntity);
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order editOrder(Order order) throws DateOrderException {
        if(order.getStartDate().after(order.getEndDate())) throw new DateOrderException("End date is before start date");
        orderRepository.saveAndFlush(orderConverter.toEntity(order));
        return order;
    }

    @Override
    public List<Order> getAll() {
        List<Order> allOrders = new ArrayList<>();
        for(OrderTableEntity orderEntity: orderRepository.findAll()) {
            allOrders.add(orderConverter.toDto(orderEntity));
        }
        return allOrders;
    }

    @Override
    public List<OrderStatus> getAllStatuses() {
        List<OrderStatus> orderStatuses = new ArrayList<>();
        for(OrderStatusTableEntity orderStatusTableEntity: orderStatusRepository.findAll()) {
            orderStatuses.add(new OrderStatus(orderStatusTableEntity.getId(), orderStatusTableEntity.getStatus()));
        }
        return orderStatuses;
    }

    @Override
    public Order getOrderById(long id) {
        return orderConverter.toDto(orderRepository.getOne(id));
    }
}
