package com.mag.lab2.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "machinist")
@XmlAccessorType(XmlAccessType.FIELD)
public class Machinist {
    @XmlElement
    private Long id;
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    private String fatherName;
    @XmlElement
    private Double valueCost;
    private Set<Order> machinistOrders;

    public Machinist(Long id, String firstName, String lastName, String fatherName, Double valueCost) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.valueCost = valueCost;
        this.machinistOrders = new HashSet<>();
    }

    public Machinist(String firstName, String lastName, String fatherName, Double valueCost) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.valueCost = valueCost;
        this.machinistOrders = new HashSet<>();
    }

    public Machinist() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Double getValueCost() {
        return valueCost;
    }

    public void setValueCost(Double valueCost) {
        this.valueCost = valueCost;
    }

    public Set<Order> getMachinistOrders() {
        return machinistOrders;
    }

    public void setMachinistOrders(Set<Order> machinistOrders) {
        this.machinistOrders = machinistOrders;
    }
}
