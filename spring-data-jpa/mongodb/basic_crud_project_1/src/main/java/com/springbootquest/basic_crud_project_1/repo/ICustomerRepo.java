package com.springbootquest.basic_crud_project_1.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springbootquest.basic_crud_project_1.model.Customer;

public interface ICustomerRepo extends MongoRepository<Customer, String> {

}
