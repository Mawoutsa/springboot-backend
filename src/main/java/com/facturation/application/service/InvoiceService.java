package com.facturation.application.service;

import com.facturation.application.DTO.invoices.CreateInvoiceDTO;
import com.facturation.application.criteria.InvoiceCriteria;
import com.facturation.application.entities.Invoice;
import com.facturation.application.entities.InvoiceStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvoiceService {
    Invoice create(CreateInvoiceDTO dto);

    Invoice update(Long id, Invoice invoiceDTO);

    Invoice delete(Long id);
    
    Invoice getInvoiceById(Long id);

    Page<Invoice> search(InvoiceCriteria criteria, int page, int size);

    Page<Invoice> read(Pageable pageable);

    Page<Invoice> searchByStatus(InvoiceStatus status, Pageable pageable); 

    Page<Invoice> searchByCustomerNameAndReference(String customerName, String reference, Pageable pageable);

}
