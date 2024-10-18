package com.facturation.application.controllers;


import com.facturation.application.DTO.invoices.CreateInvoiceDTO;
import com.facturation.application.DTO.invoices.InvoiceDTO;
import com.facturation.application.DTO.invoices.UpdateInvoiceDTO;
import com.facturation.application.criteria.InvoiceCriteria;
import com.facturation.application.entities.Invoice;
import com.facturation.application.entities.InvoiceStatus;
import com.facturation.application.mapper.InvoiceMapper;
import com.facturation.application.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/invoices")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    private final InvoiceMapper invoiceMapper;


    @PostMapping
    @Operation(summary = "Créer un nouveau invoice")
    public InvoiceDTO create(@RequestBody CreateInvoiceDTO createInvoiceDTO) {
        Invoice invoice = invoiceMapper.fromDTO(createInvoiceDTO);
        return invoiceMapper.toDTO(invoiceService.create(createInvoiceDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un invoice par son id")
    public InvoiceDTO update(@PathVariable Long id, @RequestBody UpdateInvoiceDTO updateInvoiceDTO){
        Invoice invoiceToUpdate = invoiceMapper.fromDTO(updateInvoiceDTO);
       return  invoiceMapper.toDTO(invoiceService.update(id,invoiceToUpdate));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un invoice par son id")
    public void delete(@PathVariable Long id){
          invoiceService.delete(id);
    }

    @PostMapping("/search")
    @Operation(summary = "Rechercher un invoice par son id")
    public List<InvoiceDTO> searchInvoices(
            @RequestParam(required = false) String reference,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long addressId,
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) String customerFullName,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String productName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        InvoiceCriteria criteria = new InvoiceCriteria();
        criteria.setReference(reference);
        criteria.setCustomerId(customerId);
        criteria.setUserId(userId);
        criteria.setAddressId(addressId);
        criteria.setCompanyId(companyId);
        criteria.setCustomerFullName(customerFullName);
        criteria.setFromDate(dateFormat.parse(fromDate));
        criteria.setToDate(dateFormat.parse(toDate));
        criteria.setProductName(productName);

        if (fromDate != null){
            criteria.setFromDate(dateFormat.parse(fromDate));
        }
        if (toDate != null){
            criteria.setToDate(dateFormat.parse(toDate));
        }

        Page<Invoice> invoicePage = invoiceService.search(criteria, page, size);
        return invoicePage.getContent().stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping
    @Operation(summary = "Lister toutes les invoices avec pagination")
    public Page<InvoiceDTO> read(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Page<Invoice> invoicePage = invoiceService.read(PageRequest.of(page, size));
        return invoicePage.map(inv -> invoiceMapper.toDTO(inv));
    }

    @GetMapping("/search/status")
    public Page<InvoiceDTO> searchByStatus(
            @RequestParam InvoiceStatus status, 
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Invoice> invoices = invoiceService.searchByStatus(status, PageRequest.of(page, size));
        return invoices.map(invoiceMapper::toDTO); 
    }


    @GetMapping("/search/customer-reference")
    public Page<InvoiceDTO> searchInvoices(
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String reference,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Invoice> invoicePage = invoiceService.searchByCustomerNameAndReference(customerName, reference, PageRequest.of(page,size));
        return invoicePage.map(invoiceMapper::toDTO);
    }
}
