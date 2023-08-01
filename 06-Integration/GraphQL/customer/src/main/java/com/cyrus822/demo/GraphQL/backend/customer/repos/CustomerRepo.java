package com.cyrus822.demo.GraphQL.backend.customer.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cyrus822.demo.GraphQL.backend.customer.domains.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
    @Query("select c from Customer c where c.certNo = :certNo")
    Optional<Customer> getCustomerByCertNo(@Param("certNo") String certNo);
}
