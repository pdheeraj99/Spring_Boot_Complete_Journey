package com.learning_mini_project.web_app_crm_jsp.service;

import java.util.List;

import com.learning_mini_project.web_app_crm_jsp.model.Customer;

public interface ICustomerService {
	List<Customer> getCustomerInfo();

	void registerCustomer(Customer customer);

	Customer fetchCustomerById(Integer id);

	void deleteCxRecord(Integer id);

}
