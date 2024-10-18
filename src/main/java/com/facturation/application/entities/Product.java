package com.facturation.application.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    private BigDecimal  unitPrice;

    @Column(length = 255)
    private BigDecimal  quantity;

    @Transient
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    // Méthode pour calculer le prix total
    public BigDecimal getTotalAmount() {
        return this.unitPrice.multiply(this.quantity); // Calculation with BigDecimal
    }
    
    @Column(name = "deleted")
    private  boolean deleted = false;

}
