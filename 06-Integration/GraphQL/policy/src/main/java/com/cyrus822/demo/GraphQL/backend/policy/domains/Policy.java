package com.cyrus822.demo.GraphQL.backend.policy.domains;

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
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Policy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int policyId;

    @Column(unique = true)
    private String policyKey;

    private int applicantId;

    private int beneficialId;

    private String status;

    private float amt;
}
