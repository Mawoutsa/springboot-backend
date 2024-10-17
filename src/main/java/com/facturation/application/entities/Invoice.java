package com.facturation.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String reference;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date invoiceDate;

    @Column(name = "deleted", nullable = false)
    private  boolean deleted = false;

    @Enumerated(EnumType.ORDINAL)
    private InvoiceStatus status = InvoiceStatus.EN_ATTENTE; // Par défaut à "EN_ATTENTE"


    @ManyToOne(optional = false)
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company")
    private  Company company;


    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    private List<Product> products;

}
