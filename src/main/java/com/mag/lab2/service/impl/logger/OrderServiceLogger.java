package com.mag.lab2.service.impl.logger;

import com.mag.lab2.model.dto.Order;
import com.mag.lab2.model.dto.OrderStatus;
import com.mag.lab2.model.message.ActionTypes;
import com.mag.lab2.model.message.EntityTypes;
import com.mag.lab2.model.message.LogMessage;
import com.mag.lab2.service.OrderService;
import com.mag.lab2.service.exception.DateOrderException;
import com.mag.lab2.service.logger.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderLogger")
public class OrderServiceLogger extends Logger implements OrderService {
    private final OrderService orderService;
    private final LoggerService loggerService;

    @Autowired
    public OrderServiceLogger(OrderService orderService, LoggerService loggerService) {
        this.loggerService = loggerService;
        this.orderService = orderService;
    }

    @Override
    public Order addOrder(Order order) throws DateOrderException {
        Order addedOrder =  orderService.addOrder(order);
        this.loggerService.sendLogMessage(
                new LogMessage(
                        addedOrder.getId(),
                        EntityTypes.ORDER,
                        ActionTypes.INSERT
                ));
        return addedOrder;
    }

    @Override
    public void delete(long id) {
        orderService.delete(id);
        this.loggerService.sendLogMessage(
                new LogMessage(
                        id,
                        EntityTypes.ORDER,
                        ActionTypes.DELETE
                ));
    }

    @Override
    public Order editOrder(Order order) throws DateOrderException {
        Order oldOrder = this.getOrderById(order.getId());
        Order newOrder = orderService.editOrder(order);
        LogMessage logMessage = new LogMessage(
                order.getId(),
                EntityTypes.ORDER,
                ActionTypes.UPDATE
        );
        logMessage.setChanges(this.findDiscrepancy(oldOrder, newOrder));
        this.loggerService.sendLogMessage(logMessage);
        return newOrder;
    }

    @Override
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @Override
    public List<OrderStatus> getAllStatuses() {
        return orderService.getAllStatuses();
    }

    @Override
    public Order getOrderById(long id) {
        return orderService.getOrderById(id);
    }
}
