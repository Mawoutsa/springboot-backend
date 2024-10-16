package com.facturation.application.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@Setter
@Getter
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(length = 255, nullable = false)
    private Date paymentDate;

    @Column(length = 255, nullable = false)
    private  double collectedAmount;

    @Column(length = 255)
    private String modePaiement;

    @ManyToOne(optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @Column(name = "deleted", nullable = false)
    private  boolean deleted = false;
}
