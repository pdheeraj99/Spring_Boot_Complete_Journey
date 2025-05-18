package com.learning_mini_project.web_app_crm_jsp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning_mini_project.web_app_crm_jsp.model.Customer;

@Repository
public interface ICustomerRepo extends CrudRepository<Customer, Integer> {

}
