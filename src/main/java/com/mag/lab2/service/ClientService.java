package com.mag.lab2.service;

import com.mag.lab2.model.dto.Client;

import java.util.List;

public interface ClientService {
    Client addClient(Client client);
    void delete(long id);
    Client editClient(Client client);
    List<Client> getAll();
}
