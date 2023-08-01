package com.cyrus822.demo.GraphQL.backend.customer.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cyrus822.demo.GraphQL.backend.customer.domains.CustPolicy;

public interface CustPolicyRepo extends JpaRepository<CustPolicy, Integer>{
    @Query("select cp from CustPolicy cp where cp.custId = :custId")
    List<CustPolicy> getCustomerPolicyByCustId(@Param("custId") int custId);
}
