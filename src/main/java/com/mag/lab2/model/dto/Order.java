package com.mag.lab2.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Order {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Long id;
    private String description;
    private Client client;
    private Machinist machinist;
    private Double cost;
    private OrderStatus status;

    public Order(Long id, String description, Date startDate, Date endDate, Double cost) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    public Order(String description, Date startDate, Date endDate, Double cost) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    public Order() {
        this.client = new Client();
        this.machinist = new Machinist();
        this.status = new OrderStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Machinist getMachinist() {
        return machinist;
    }

    public void setMachinist(Machinist machinist) {
        this.machinist = machinist;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
