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
public class CustPolicy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cusPolId;

    @Column(nullable =  false)
    private int appCusId;

    @Column(nullable =  false)
    private int beneCusId;
}
