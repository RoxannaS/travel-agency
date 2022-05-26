package com.adobe.travelagency.controller;

import com.adobe.travelagency.entity.Customer;
import com.adobe.travelagency.service.AgentService;
import com.adobe.travelagency.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    CustomerService customerService;
    AgentService agentService;

    public CustomerController(CustomerService customerService, AgentService agentService) {
        this.customerService = customerService;
        this.agentService = agentService;
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {

        Optional<Customer> savedCustomer = customerService.save(customer);
        if (savedCustomer.isPresent()) {
            return new ResponseEntity<>(savedCustomer.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
