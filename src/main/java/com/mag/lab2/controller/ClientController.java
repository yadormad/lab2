package com.mag.lab2.controller;

import com.mag.lab2.model.dto.Client;
import com.mag.lab2.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(@Qualifier("clientLogger") ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String getAllClients(Model model) {
        model.addAttribute("clientList", clientService.getAll());
        model.addAttribute("client", new Client());
        return "clients";
    }

    @GetMapping("/")
    public String defaultRedirect(Model model) {
        return getAllClients(model);
    }

    @RequestMapping(value="/clients/edit", method=RequestMethod.POST)
    public String editClient(@ModelAttribute Client client) {
        clientService.editClient(client);
        return "redirect:/clients";
    }

    @RequestMapping(value="/clients/add", method=RequestMethod.POST)
    public String addClient(@ModelAttribute Client client) {
        clientService.addClient(client);
        return "redirect:/clients";
    }

    @RequestMapping(value="/clients/delete", method=RequestMethod.POST)
    public String deleteClient(@ModelAttribute Client client) {
        clientService.delete(client.getId());
        return "redirect:/clients";
    }
}
