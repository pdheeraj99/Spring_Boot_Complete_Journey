package com.springbootquest.basic_crud_project_1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer {

    @Id
    private String id;

    private Integer customerNumber;

    private String name;

    private String city;

    public Customer() {
        super();
    }

    public Customer(String id, Integer customerNumber, String name, String city) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.name = name;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", customerNumber=" + customerNumber + ", name=" + name + ", city=" + city + "]";
    }

}
