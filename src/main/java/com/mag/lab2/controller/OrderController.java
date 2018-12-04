package com.mag.lab2.controller;

import com.mag.lab2.model.Order;
import com.mag.lab2.service.ClientService;
import com.mag.lab2.service.MachinistService;
import com.mag.lab2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final ClientService clientService;
    private final MachinistService machinistService;

    @Autowired
    public OrderController(OrderService orderService, ClientService clientService, MachinistService machinistService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.machinistService = machinistService;
    }

    @GetMapping("/orders")
    public String getAllOrders(Model model) {
        model.addAttribute("ordersList", orderService.getAll());
        model.addAttribute("clientsList", clientService.getAll());
        model.addAttribute("machinistList", machinistService.getAll());
        model.addAttribute("statusList", orderService.getAllStatuses());
        model.addAttribute("order", new Order());
        return "orders";
    }

    @RequestMapping(value="/orders/edit", method= RequestMethod.POST)
    public String editOrder(@ModelAttribute Order order) {
        orderService.editOrder(order);
        return "redirect:/orders";
    }

    @RequestMapping(value="/orders/add", method=RequestMethod.POST)
    public String addOrder(@ModelAttribute Order order) {
        orderService.addOrder(order);
        return "redirect:/orders";
    }

    @RequestMapping(value="/orders/delete", method=RequestMethod.POST)
    public String deleteOrder(@ModelAttribute Order order) {
        orderService.delete(order.getId());
        return "redirect:/orders";
    }
}
