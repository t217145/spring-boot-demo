package com.cyrus822.demo.GraphQL.Basic.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyrus822.demo.GraphQL.Basic.backend.domains.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Integer> {

}