package com.cyrus822.demo.GraphQL.Basic.backend.domains;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Items implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(unique = true, nullable = false)
    private String itemCode;

    @Column(nullable = false)
    private String itemName;

    private float unitPrice;
}
