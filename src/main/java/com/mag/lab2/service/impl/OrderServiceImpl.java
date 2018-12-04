package com.mag.lab2.service.impl;

import com.mag.lab2.entity.ClientTableEntity;
import com.mag.lab2.entity.MachinistTableEntity;
import com.mag.lab2.entity.OrderStatusTableEntity;
import com.mag.lab2.entity.OrderTableEntity;
import com.mag.lab2.model.Order;
import com.mag.lab2.model.OrderStatus;
import com.mag.lab2.repository.ClientRepository;
import com.mag.lab2.repository.MachinistRepository;
import com.mag.lab2.repository.OrderRepository;
import com.mag.lab2.repository.OrderStatusRepository;
import com.mag.lab2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final MachinistRepository machinistRepository;
    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ClientRepository clientRepository, MachinistRepository machinistRepository, OrderStatusRepository orderStatusRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.machinistRepository = machinistRepository;
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public Order addOrder(Order order) {
        OrderTableEntity orderEntity = new OrderTableEntity();
        orderEntity.toEntity(order);
        importRelations(orderEntity, order);
        orderRepository.saveAndFlush(orderEntity);
        return orderEntity.toModel();
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order editOrder(Order order) {
        OrderTableEntity orderEntity = orderRepository.getOne(order.getId());
        orderEntity.toEntity(order);
        importRelations(orderEntity, order);
        orderRepository.saveAndFlush(orderEntity);
        return orderEntity.toModel();
    }

    @Override
    public List<Order> getAll() {
        List<Order> allOrders = new ArrayList<>();
        for(OrderTableEntity orderEntity: orderRepository.findAll()) {
            allOrders.add(orderEntity.exportRelations(orderEntity.toModel()));
        }
        return allOrders;
    }

    @Override
    public List<OrderStatus> getAllStatuses() {
        List<OrderStatus> orderStatuses = new ArrayList<>();
        for(OrderStatusTableEntity orderStatusTableEntity: orderStatusRepository.findAll()) {
            orderStatuses.add(orderStatusTableEntity.toModel());
        }
        return orderStatuses;
    }

    private void importRelations(OrderTableEntity orderEntity, Order orderModel) {
        Optional<ClientTableEntity> clientEntityOptional = clientRepository.findById(orderModel.getClient().getId());
        Optional<MachinistTableEntity> machinistEntityOptional = machinistRepository.findById(orderModel.getMachinist().getId());
        Optional<OrderStatusTableEntity> orderStatusEntityOptional = orderStatusRepository.findById(orderModel.getStatus().getId());
        clientEntityOptional.ifPresent(orderEntity::setClientEntity);
        machinistEntityOptional.ifPresent(orderEntity::setMachinistEntity);
        orderStatusEntityOptional.ifPresent(orderEntity::setOrderStatusEntity);
    }
}
