package com.mag.lab2.service.impl;

import com.mag.lab2.entity.ClientTableEntity;
import com.mag.lab2.model.Client;
import com.mag.lab2.repository.ClientRepository;
import com.mag.lab2.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client addClient(Client client) {
        ClientTableEntity clientEntity = new ClientTableEntity();
        clientEntity.toEntity(client);
        clientRepository.saveAndFlush(clientEntity);
        return clientEntity.toModel();
    }

    @Override
    public void delete(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client editClient(Client client) {
        ClientTableEntity clientEntity = clientRepository.getOne(client.getId());
        clientEntity.toEntity(client);
        clientRepository.saveAndFlush(clientEntity);
        return clientEntity.toModel();
    }

    @Override
    public List<Client> getAll() {
        List<Client> allClients = new ArrayList<>();
        for(ClientTableEntity clientEntity: clientRepository.findAll()) {
            allClients.add(clientEntity.exportOrders(clientEntity.toModel()));
        }
        return allClients;
    }
}
