package com.cyrus822.basicform.models;

import java.io.Serializable;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class MyObjects implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(min = 3, max = 100, message = "Field 1 value must between 3 to 100 characters")
    @Column(length = 100, nullable = false)
    private String fld1;
}
