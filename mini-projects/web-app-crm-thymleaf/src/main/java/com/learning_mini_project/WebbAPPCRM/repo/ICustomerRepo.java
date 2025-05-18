package com.learning_mini_project.WebbAPPCRM.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning_mini_project.WebbAPPCRM.model.Customer;

@Repository
public interface ICustomerRepo extends CrudRepository<Customer, Integer> {

}
