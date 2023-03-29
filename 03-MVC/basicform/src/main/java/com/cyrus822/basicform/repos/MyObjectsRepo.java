package com.cyrus822.basicform.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cyrus822.basicform.models.MyObjects;

public interface MyObjectsRepo extends JpaRepository<MyObjects, Integer> {
    
}
