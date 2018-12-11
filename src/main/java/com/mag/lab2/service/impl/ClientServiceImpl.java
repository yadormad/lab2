package com.mag.lab2.service.impl;

import com.mag.lab2.model.entity.ClientTableEntity;
import com.mag.lab2.model.dto.Client;
import com.mag.lab2.repository.ClientRepository;
import com.mag.lab2.service.ClientService;
import com.mag.lab2.service.converter.Converter;
import com.mag.lab2.service.converter.impl.ClientJPAConverterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private Converter<ClientTableEntity, Client> clientConverter;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.clientConverter = new ClientJPAConverterImpl(clientRepository);
    }

    @Override
    public Client addClient(Client client) {
        ClientTableEntity clientEntity = clientConverter.toEntity(client);
        clientRepository.saveAndFlush(clientEntity);
        return clientConverter.toDto(clientEntity);
    }

    @Override
    public void delete(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client editClient(Client client) {
        clientRepository.saveAndFlush(clientConverter.toEntity(client));
        return client;
    }

    @Override
    public List<Client> getAll() {
        List<Client> allClients = new ArrayList<>();
        for(ClientTableEntity clientEntity: clientRepository.findAll()) {
            allClients.add(clientConverter.toDto(clientEntity));
        }
        return allClients;
    }


}
