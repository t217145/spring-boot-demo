package com.cyrus822.demo.GraphQL.backend.customer.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyrus822.demo.GraphQL.backend.customer.domains.CustPolicy;

public interface CustPolicyRepo extends JpaRepository<CustPolicy, Integer>{
    
}
