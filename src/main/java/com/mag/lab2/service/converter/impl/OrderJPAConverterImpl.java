package com.mag.lab2.service.converter.impl;

import com.mag.lab2.model.dto.Client;
import com.mag.lab2.model.dto.Machinist;
import com.mag.lab2.model.dto.Order;
import com.mag.lab2.model.dto.OrderStatus;
import com.mag.lab2.model.entity.ClientTableEntity;
import com.mag.lab2.model.entity.MachinistTableEntity;
import com.mag.lab2.model.entity.OrderStatusTableEntity;
import com.mag.lab2.model.entity.OrderTableEntity;
import com.mag.lab2.repository.ClientRepository;
import com.mag.lab2.repository.MachinistRepository;
import com.mag.lab2.repository.OrderRepository;
import com.mag.lab2.repository.OrderStatusRepository;
import com.mag.lab2.service.converter.Converter;

import java.sql.Date;
import java.util.Optional;

public class OrderJPAConverterImpl implements Converter<OrderTableEntity, Order> {

    private OrderRepository orderRepository;
    private ClientRepository clientRepository;
    private MachinistRepository machinistRepository;
    private OrderStatusRepository orderStatusRepository;
    private Converter<ClientTableEntity, Client> clientConverter;
    private Converter<MachinistTableEntity, Machinist> machinistConverter;

    public OrderJPAConverterImpl(OrderRepository orderRepository,
                                 ClientRepository clientRepository,
                                 MachinistRepository machinistRepository,
                                 OrderStatusRepository orderStatusRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.machinistRepository = machinistRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.clientConverter = new ClientJPAConverterImpl(clientRepository);
        this.machinistConverter = new MachinistJPAConverterImpl(machinistRepository);
    }

    @Override
    public OrderTableEntity toEntity(Order order) {
        OrderTableEntity orderTableEntity = (order.getId() != null) ? orderRepository.getOne(order.getId()) : new OrderTableEntity();
        orderTableEntity.setDescription(order.getDescription());
        orderTableEntity.setStartDate(new Date(order.getStartDate().getTime()));
        orderTableEntity.setEndDate(new Date(order.getEndDate().getTime()));
        orderTableEntity.setFullCost(order.getCost());
        return importRelations(orderTableEntity, order);
    }

    @Override
    public Order toDto(OrderTableEntity orderTableEntity) {
        Order order = new Order(orderTableEntity.getId(),
                orderTableEntity.getDescription(),
                orderTableEntity.getStartDate(),
                orderTableEntity.getEndDate(),
                orderTableEntity.getFullCost());
        order.setClient(clientConverter.toDto(orderTableEntity.getClientEntity()));
        order.setMachinist(machinistConverter.toDto(orderTableEntity.getMachinistEntity()));
        OrderStatusTableEntity orderStatusTableEntity = orderTableEntity.getOrderStatusEntity();
        order.setStatus(new OrderStatus(orderStatusTableEntity.getId(), orderStatusTableEntity.getStatus()));
        return order;
    }

    private OrderTableEntity importRelations(OrderTableEntity orderTableEntity, Order order) {
        Optional<ClientTableEntity> clientEntityOptional = clientRepository.findById(order.getClient().getId());
        Optional<MachinistTableEntity> machinistEntityOptional = machinistRepository.findById(order.getMachinist().getId());
        Optional<OrderStatusTableEntity> orderStatusEntityOptional = orderStatusRepository.findById(order.getStatus().getId());
        clientEntityOptional.ifPresent(orderTableEntity::setClientEntity);
        machinistEntityOptional.ifPresent(orderTableEntity::setMachinistEntity);
        orderStatusEntityOptional.ifPresent(orderTableEntity::setOrderStatusEntity);
        return orderTableEntity;
    }
}
