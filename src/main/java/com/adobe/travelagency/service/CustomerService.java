package com.adobe.travelagency.service;

import com.adobe.travelagency.entity.Customer;
import com.adobe.travelagency.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> save(Customer customer) {
        return Optional.of(customerRepository.save(customer));
    }
}
