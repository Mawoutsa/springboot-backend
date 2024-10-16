package com.facturation.application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String companyName;

    @Column(length = 255)
    private String companyTagline;

    @Column(length = 255)
    private String companyPoBox;

    @Column(length = 255)
    private String emailCompany;

    @Column(length = 255)
    private String siteWebCompany;

    @Column(length = 255)
    private String cityCompany;

    @Column(length = 255)
    private String neighborhoodCompany;

    @Column(length = 255)
    private String phoneNumberCompany;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Invoice> invoices = new HashSet<>();

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;
}
