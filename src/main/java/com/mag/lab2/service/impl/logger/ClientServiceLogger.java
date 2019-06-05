package com.mag.lab2.service.impl.logger;

import com.mag.lab2.model.dto.Client;
import com.mag.lab2.model.message.ActionTypes;
import com.mag.lab2.model.message.EntityTypes;
import com.mag.lab2.model.message.LogMessage;
import com.mag.lab2.service.ClientService;
import com.mag.lab2.service.logger.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clientLogger")
public class ClientServiceLogger extends Logger implements ClientService {
    private final ClientService clientService;
    private final LoggerService loggerService;

    @Autowired
    public ClientServiceLogger(ClientService clientService, LoggerService loggerService) {
        this.loggerService = loggerService;
        this.clientService = clientService;
    }

    @Override
    public Client addClient(Client client) {
        Client addedClient =  clientService.addClient(client);
        this.loggerService.sendLogMessage(
                new LogMessage(
                        addedClient.getId(),
                        EntityTypes.CLIENT,
                        ActionTypes.INSERT
                ));
        return addedClient;
    }

    @Override
    public void delete(long id) {
        clientService.delete(id);
        this.loggerService.sendLogMessage(
                new LogMessage(
                        id,
                        EntityTypes.CLIENT,
                        ActionTypes.DELETE
                ));
    }

    @Override
    public Client editClient(Client client) {
        Client oldClient = this.getClientById(client.getId());
        Client newClient = clientService.editClient(client);
        LogMessage logMessage = new LogMessage(
                client.getId(),
                EntityTypes.CLIENT,
                ActionTypes.UPDATE
        );
        logMessage.setChanges(this.findDiscrepancy(oldClient, newClient));
        this.loggerService.sendLogMessage(logMessage);
        return newClient;
    }

    @Override
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @Override
    public Client getClientById(long id) {
        return clientService.getClientById(id);
    }

}
