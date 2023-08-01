package com.cyrus822.demo.GraphQL.backend.policy.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cyrus822.demo.GraphQL.backend.policy.domains.Policy;

public interface PolicyRepo extends JpaRepository<Policy, Integer>{
    @Query("select p from Policy p where p.policyKey = :policyKey")
    Optional<Policy> getPolicyByPolicyKey(@Param("policyKey") String policyKey);
}
