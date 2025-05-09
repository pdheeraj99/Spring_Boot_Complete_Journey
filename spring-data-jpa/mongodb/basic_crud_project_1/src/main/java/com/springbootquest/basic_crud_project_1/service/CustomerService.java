package com.springbootquest.basic_crud_project_1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootquest.basic_crud_project_1.dto.CustomerDto;
import com.springbootquest.basic_crud_project_1.model.Customer;
import com.springbootquest.basic_crud_project_1.repo.ICustomerRepo;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepo repo;

    @Override
    public String registerCustomer(CustomerDto custDto) {

        Customer cx = new Customer();
        BeanUtils.copyProperties(custDto, cx);
        Customer cxDoc = repo.save(cx);
        return "ID which our data stored is :::" + cxDoc.getId();

    }

    @Override
    public List<Customer> findAllCustomer() {

        return repo.findAll();

    }

    @Override
    public String removeCustomerInfoById(String id) {
        Optional<Customer> optional = repo.findById(id);
        if (optional.isPresent()) {
            repo.deleteById(id);
            return "Customer record is deleted with ID:::" + id;
        }
        return "No Customer found with this id";
    }

}
