package com.mag.lab2.service.converter.impl;

import com.mag.lab2.model.dto.Client;
import com.mag.lab2.model.entity.ClientTableEntity;
import com.mag.lab2.service.converter.ConverterFabric;

public class ClientJPAConverterImpl implements ConverterFabric<ClientTableEntity, Client> {
    @Override
    public ClientTableEntity toEntity(Client client) {
        return null;
    }

    @Override
    public Client toDto(ClientTableEntity clientTableEntity) {
        return null;
    }
}
