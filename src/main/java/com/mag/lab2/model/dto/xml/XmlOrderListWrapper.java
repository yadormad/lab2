package com.mag.lab2.model.dto.xml;

import com.mag.lab2.model.dto.Order;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="orders")
public class XmlOrderListWrapper {

    private List<Order> orders;

    public XmlOrderListWrapper(List<Order> orders) {
        this.orders = orders;
    }

    public XmlOrderListWrapper() {
        this.orders = new ArrayList<>();
    }

    @XmlElement(name="order")
    public List<Order> getOrders() {
        return orders;
    }
}
