package com.cyrus822.demo.GraphQL.backend.customer.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cyrus822.demo.GraphQL.backend.customer.domains.CustPolicy;
import com.cyrus822.demo.GraphQL.backend.customer.domains.Customer;
import com.cyrus822.demo.GraphQL.backend.customer.repos.CustPolicyRepo;
import com.cyrus822.demo.GraphQL.backend.customer.repos.CustomerRepo;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepo repo;

    @Autowired
    private CustPolicyRepo cusPolicyRepo;
    
    @PostMapping("/getCustomerById")
    public Customer getCustomerById(@RequestBody int cusId){
        Optional<Customer> oCust = repo.findById(cusId);
        Customer rtn = null;
        if(oCust.isPresent()){
            rtn = oCust.get();
        }
        return rtn;
    }

    @PostMapping("/getCustomerByCertNo")
    public Customer getCustomerByCertNo(@RequestBody String certNo){
        Optional<Customer> oCust = repo.getCustomerByCertNo(certNo);
        Customer rtn = null;
        if(oCust.isPresent()){
            rtn = oCust.get();
        }
        return rtn;
    }    

    @PostMapping("/cusPolicy")
    public List<CustPolicy> getCustomerPolicy(@RequestBody int cusId){
        return cusPolicyRepo.getCustomerPolicyByCustId(cusId);
    }
}