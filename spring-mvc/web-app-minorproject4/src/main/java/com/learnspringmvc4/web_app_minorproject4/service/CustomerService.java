package com.learnspringmvc4.web_app_minorproject4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnspringmvc4.web_app_minorproject4.model.Customer;
import com.learnspringmvc4.web_app_minorproject4.repo.ICustomerRepo;

@Service
public class CustomerService implements ICustomerService {

    private ICustomerRepo repo;

    @Autowired
    public void setRepo(ICustomerRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Customer> getCustomersInfo() {
        return (List<Customer>) repo.findAll();
    }

    @Override
    public void registerCustomer(Customer customer) {
        repo.save(customer);
    }

    @Override
    public Customer fetchCustomerById(Integer id) {
        // *** Optional *** is like a box—it might have a Customer inside, or it might
        // be empty
        // if no customer with that id exists. It’s a safe way to avoid errors when
        // something might not be found.

        Optional<Customer> optional = repo.findById(id);

        // get() opens the Optional box and takes out the Customer inside.
        // If there’s a Customer, it returns it. Done!
        // But if the box is empty (no customer found), it’ll throw an exception called
        // NoSuchElementException. Basically, it crashes with an error saying, “Hey,
        // there’s nothing here!”
        return optional.get();

        // Use ifpresent, orElse, orElseThrow for optional to get better code
    }

}
