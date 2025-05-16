package com.learnspringmvc4.web_app_minorproject4.repo;

import org.springframework.data.repository.CrudRepository;

import com.learnspringmvc4.web_app_minorproject4.model.Customer;

public interface ICustomerRepo extends CrudRepository<Customer, Integer> {

}
