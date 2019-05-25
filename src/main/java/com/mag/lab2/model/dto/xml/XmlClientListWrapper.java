package com.mag.lab2.model.dto.xml;

import com.mag.lab2.model.dto.Client;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="clients")
public class XmlClientListWrapper {

    private List<Client> clients;

    public XmlClientListWrapper(List<Client> clients) {
        this.clients = clients;
    }

    public XmlClientListWrapper() {
        this.clients = new ArrayList<>();
    }

    @XmlElement(name="client")
    public List<Client> getClients() {
        return clients;
    }
}
