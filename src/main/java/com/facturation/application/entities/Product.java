package com.facturation.application.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String productReference;

    @Column(length = 255)
    private String description;

    @Column(length = 255)
    private Double unitPrice;

    @Column(length = 255)
    private Double quantity;

    @Transient
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    // Méthode pour calculer le prix total
    public Double getTotalAmount() {
        return unitPrice * quantity;
    }
    @Column(name = "deleted")
    private  boolean deleted = false;

}
