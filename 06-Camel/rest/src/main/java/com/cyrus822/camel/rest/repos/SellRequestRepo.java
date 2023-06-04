package com.cyrus822.camel.rest.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyrus822.camel.rest.domains.SellRequest;

public interface SellRequestRepo extends JpaRepository<SellRequest, Integer> {

}
