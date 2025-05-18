package com.learnspringmvc4.web_app_minorproject4.web;

import java.util.List;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.learnspringmvc4.web_app_minorproject4.model.Customer;
import com.learnspringmvc4.web_app_minorproject4.service.ICustomerService;

@Controller
public class CustomerController {

    @Autowired
    private ICustomerService service;

    // => Model is a Spring object to pass data from controller to view
    // => Spring auto-injects Model object if you declare it as a parameter. It's a
    // Spring-managed bean
    // => Parameter order doesn't matter. Spring identifies Model by its type, not
    // position (Model, xxxx) or (xxxx, Model)
    @GetMapping("/cx-info")
    public String getCxData(Model model) {
        List<Customer> customers = service.getCustomersInfo();
        model.addAttribute("customers", customers);
        customers.forEach(x -> System.out.println(x));
        return "customerinfo";
    }

}
