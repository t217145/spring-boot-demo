package com.cyrus822.demo.GraphQL.backend.customer.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cyrus822.demo.GraphQL.backend.customer.domains.Customer;
import com.cyrus822.demo.GraphQL.backend.customer.repos.CustomerRepo;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepo repo;
    
    @PostMapping("/customer")
    public Customer getCustomerByCusId(@RequestBody int cusId){
        Optional<Customer> oCust = repo.findById(cusId);
        Customer rtn = null;
        if(oCust.isPresent()){
            rtn = oCust.get();
        }
        return rtn;
    }
}
