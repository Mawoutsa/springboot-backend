package com.facturation.application.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private  String firstName;

    @Column(length = 255)
    private  String lastName;

    @Column(name = "deleted")
    private  boolean deleted = false;

    @Column(length = 255)
    private String cityCustomer;

    @Column(length = 255)
    private String neighborhoodCustomer;

    @Column(length = 255)
    private String phoneNumberCustomer;

    @Column(length = 255)
    private  String email;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Invoice> invoices = new HashSet<>();

}
