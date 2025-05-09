package com.springbootquest.basic_crud_project_1.service;

import java.util.List;

import com.springbootquest.basic_crud_project_1.dto.CustomerDto;
import com.springbootquest.basic_crud_project_1.model.Customer;

public interface ICustomerService {

    String registerCustomer(CustomerDto custDto);

    List<Customer> findAllCustomer();

    String removeCustomerInfoById(String id);

}
