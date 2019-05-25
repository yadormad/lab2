package com.mag.lab2.controller.rest;

import com.mag.lab2.model.dto.xml.XmlClientListWrapper;
import com.mag.lab2.model.dto.xml.XmlMachinistListWrapper;
import com.mag.lab2.model.dto.xml.XmlOrderListWrapper;
import com.mag.lab2.service.ClientService;
import com.mag.lab2.service.MachinistService;
import com.mag.lab2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class EntityRestController {
    private final OrderService orderService;
    private final ClientService clientService;
    private final MachinistService machinistService;

    @Autowired
    public EntityRestController(OrderService orderService, ClientService clientService, MachinistService machinistService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.machinistService = machinistService;
    }

    @RequestMapping(
            value="/clients",
            method= RequestMethod.GET,
            produces = { "application/json", "application/xml" }
    )
    public XmlClientListWrapper getAllClients() {
        return new XmlClientListWrapper(clientService.getAll());
    }

    @RequestMapping(
            value="/orders",
            method= RequestMethod.GET,
            produces = { "application/json", "application/xml" }
    )
    public XmlOrderListWrapper getAllOrders() {
        return new XmlOrderListWrapper(orderService.getAll());
    }

    @RequestMapping(
            value="/edit",
            method= RequestMethod.GET,
            produces = { "application/json", "application/xml" }
    )
    public XmlMachinistListWrapper getAllMachinists() {
        return new XmlMachinistListWrapper(machinistService.getAll());
    }
}
