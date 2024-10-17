package com.facturation.application.dao;

import com.facturation.application.entities.Invoice;
import com.facturation.application.entities.InvoiceStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {
    Page<Invoice> findAll(Pageable pageable);

    Page<Invoice> findAllByStatus(InvoiceStatus status, Pageable pageable); 

    @Query("SELECT i FROM Invoice i JOIN i.customer c " +
    "WHERE (:customerName IS NULL OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :customerName, '%')) OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :customerName, '%'))) " +
    "AND (:reference IS NULL OR i.reference LIKE CONCAT('%', :reference, '%'))")
Page<Invoice> findAllByCustomerNameAndReference(
     @Param("customerName") String customerName, 
     @Param("reference") String reference, 
     Pageable pageable);
}
