package com.cyrus822.demo.GraphQL.Basic.backend.domains;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    private String cusName;

    private float overAllDiscount;
}