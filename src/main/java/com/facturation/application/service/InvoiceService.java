package com.facturation.application.service;

import com.facturation.application.DTO.invoices.CreateInvoiceDTO;
import com.facturation.application.criteria.InvoiceCriteria;
import com.facturation.application.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvoiceService {
    Invoice create(CreateInvoiceDTO dto);

    Invoice update(Long id, Invoice invoiceDTO);

    Invoice delete(Long id);

    Page<Invoice> search(InvoiceCriteria criteria, int page, int size);

    Page<Invoice> read(Pageable pageable);


}
