package com.learning_mini_project.web_app_crm_jsp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learning_mini_project.web_app_crm_jsp.model.Customer;
import com.learning_mini_project.web_app_crm_jsp.service.ICustomerService;

@Controller
public class CustomerController {
	@Autowired
	private ICustomerService service;

	@GetMapping("/cx-info")
	public String getCxData(Model model) {
		System.out.println("Controller cx info");
		List<Customer> customers = service.getCustomerInfo();
		model.addAttribute("customers", customers);
		customers.forEach(c -> System.out.println(c));// debugging
		return "customerinfo";
	}

	@GetMapping("/show-form")
	public String showForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "showform";
	}

	@PostMapping("/register-cx")
	public String registerCx(@ModelAttribute("customer") Customer customer, Model model) {
		service.registerCustomer(customer);
		return "redirect:/cx-info";
	}

	@GetMapping("/update-form")
	public String updateCxInfo(@RequestParam("cxid") Integer cxid, Model model) {
		Customer cx = service.fetchCustomerById(cxid);
		model.addAttribute("customer", cx);
		return "updateform";
	}

	@GetMapping("/delete-data")
	public String DeleteCxInfo(@RequestParam("cxid") Integer cxid) {
		System.out.println("/delete-data");
		System.out.println(cxid);
		service.deleteCxRecord(cxid);

		System.out.println("Deleted");
		return "redirect:/cx-info";
	}

}
