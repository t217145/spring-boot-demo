package com.cyrus822.demo.GraphQL.backend.customer.domains;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int custId;

    @Column(nullable =  false, unique = true)
    private String certNo;

    @Column(nullable =  false)
    private String firstName;

    @Column(nullable =  false)
    private String lastName;
}
