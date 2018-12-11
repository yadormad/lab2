package com.mag.lab2.service.converter.impl;

import com.mag.lab2.model.dto.Client;
import com.mag.lab2.model.entity.ClientTableEntity;
import com.mag.lab2.repository.ClientRepository;
import com.mag.lab2.service.converter.Converter;

public class ClientJPAConverterImpl implements Converter<ClientTableEntity, Client> {

    private ClientRepository clientRepository;

    public ClientJPAConverterImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientTableEntity toEntity(Client client) {
        ClientTableEntity clientTableEntity = (client.getId() != null) ? clientRepository.getOne(client.getId()) : new ClientTableEntity();
        clientTableEntity.setFirstname(client.getFirstName());
        clientTableEntity.setLastname(client.getLastName());
        clientTableEntity.setFathername(client.getFatherName());
        clientTableEntity.setPhone(client.getPhoneNumber());
        return clientTableEntity;
    }

    @Override
    public Client toDto(ClientTableEntity clientTableEntity) {
        return new Client(clientTableEntity.getId(), clientTableEntity.getFirstname(), clientTableEntity.getLastname(), clientTableEntity.getFathername(), clientTableEntity.getPhone());
    }
}
