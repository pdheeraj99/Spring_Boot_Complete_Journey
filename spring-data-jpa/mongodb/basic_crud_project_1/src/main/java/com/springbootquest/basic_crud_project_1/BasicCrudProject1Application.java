package com.springbootquest.basic_crud_project_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.springbootquest.basic_crud_project_1.service.CustomerService;

@SpringBootApplication
public class BasicCrudProject1Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext container = SpringApplication.run(BasicCrudProject1Application.class, args);
		CustomerService service = container.getBean(CustomerService.class);

		// Registering the customer

		// String status = service.registerCustomer(new
		// CustomerDto(IDGeneratorUtil.generateId(), 1, "Dheeraj", "Vizag"));
		// System.out.println(status);

		System.out.println("********************");

		// Finding All the Customers

		service.findAllCustomer().forEach(x -> System.out.println(x));

		System.out.println("********************");

		// Removing the customer with ID

		System.out.println("Removing the customers by ID");

		String dStatus = service.removeCustomerInfoById("2c55153f07");
		System.out.println("Status is ->" + dStatus);

	}

}
