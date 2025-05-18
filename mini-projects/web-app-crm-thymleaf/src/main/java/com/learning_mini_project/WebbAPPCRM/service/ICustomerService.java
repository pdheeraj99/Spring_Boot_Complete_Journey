package com.learning_mini_project.WebbAPPCRM.service;

import java.util.List;

import com.learning_mini_project.WebbAPPCRM.model.Customer;

public interface ICustomerService {
	List<Customer> getCustomerInfo();

	void registerCustomer(Customer customer);

	Customer fetchCustomerById(Integer id);

	void deleteCxRecord(Integer id);

}
