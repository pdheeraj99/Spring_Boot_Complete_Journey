package com.learnspringmvc4.web_app_minorproject4.service;

import java.util.List;

import com.learnspringmvc4.web_app_minorproject4.model.Customer;

public interface ICustomerService {

    List<Customer> getCustomersInfo();

    void registerCustomer(Customer customer);

    Customer fetchCustomerById(Integer id);

}
