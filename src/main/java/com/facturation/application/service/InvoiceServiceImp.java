package com.facturation.application.service;

import com.facturation.application.DTO.invoices.CreateInvoiceDTO;
import com.facturation.application.DTO.products.CreateProductDTO;
import com.facturation.application.criteria.InvoiceCriteria;
import com.facturation.application.criteria.InvoiceSpecification;
import com.facturation.application.dao.*;
import com.facturation.application.entities.*;
import com.facturation.application.mapper.InvoiceMapper;
import com.facturation.application.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class InvoiceServiceImp implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final CustomerRepository customerRepository;

    private final CompanyRepository companyRepository;

    private final ProductRepository productRepository;

    private final InvoiceMapper invoiceMapper;

    private final ProductMapper productMapper;



    @Override
    @Transactional
    public Invoice create (CreateInvoiceDTO dto) {

        // Créer une nouvelle entreprise (Company)
        Company company = new Company();
        company.setCompanyName(dto.getCompany().getCompanyName());
        company.setCompanyTagline(dto.getCompany().getCompanyTagline());
        company.setCompanyPoBox(dto.getCompany().getCompanyPoBox());
        company.setSiteWebCompany(dto.getCompany().getSiteWebCompany());
        company.setCityCompany(dto.getCompany().getCityCompany());
        company.setEmailCompany(dto.getCompany().getEmailCompany());
        company.setNeighborhoodCompany(dto.getCompany().getNeighborhoodCompany());
        company.setPhoneNumberCompany(dto.getCompany().getPhoneNumberCompany());
        companyRepository.save(company);

        // Créer un nouveau client (Customer)
        Customer customer = new Customer();
        customer.setFirstName(dto.getCustomer().getFirstName());
        customer.setLastName(dto.getCustomer().getLastName());
        customer.setEmail(dto.getCustomer().getEmail());
        customer.setPhoneNumberCustomer(dto.getCustomer().getPhoneNumberCustomer());
        customer.setCityCustomer(dto.getCustomer().getCityCustomer());
        customer.setNeighborhoodCustomer(dto.getCustomer().getNeighborhoodCustomer());
        customerRepository.save(customer);

        // Créer la facture (Invoice)
        Invoice invoice = new Invoice();
        invoice.setReference(dto.getReference());
        invoice.setInvoiceDate(dto.getInvoiceDate());

        if (dto.getStatus() == null) {
            invoice.setStatus(InvoiceStatus.EN_ATTENTE); 
        } else {
            invoice.setStatus(dto.getStatus());
        }
                
        invoice.setCompany(company);
        invoice.setCustomer(customer);
        invoice.setDeleted(false);

        invoice = invoiceRepository.save(invoice);


    
    List<Product> products = new ArrayList<>();

    for (CreateProductDTO productDTO : dto.getProducts()) {
        Product product = new Product();
        product.setProductReference(productDTO.getProductReference());
        product.setDescription(productDTO.getDescription());
        product.setUnitPrice(BigDecimal.valueOf(productDTO.getUnitPrice())); // Convert to BigDecimal
        product.setQuantity(BigDecimal.valueOf(productDTO.getQuantity())); // Convert to BigDecimal
        product.setInvoice(invoice);
        productRepository.save(product);
        products.add(product);
    }

    invoice.setProducts(products);
    return invoiceRepository.save(invoice);
    }

        @Override
    public Invoice update(Long id, Invoice invoiceDTO) {
        return invoiceRepository.findById(id)
                .map(i ->
                {
                    i.setReference(invoiceDTO.getReference());
                    i.setInvoiceDate((invoiceDTO.getInvoiceDate()));
                    return invoiceRepository.save(i);
                }).orElseThrow(() -> new RuntimeException("Invoice non trouver !"));
    }

    @Override
    public Invoice delete(Long id) {
        return invoiceRepository.findById(id).map(invoice -> {
            invoice.setDeleted(true);
            return invoiceRepository.save(invoice);
        }).orElseThrow(() -> new RuntimeException("Invoice non triuve!"));
    }

    @Override
    public Page<Invoice> search(InvoiceCriteria criteria, int page, int size) {
        Specification<Invoice> specification = null;

                if (criteria.getReference() !=null ) {
                    specification = Specification.where(InvoiceSpecification.referenceIs(criteria.getReference()));
                }

                if (criteria.getUserId() !=null) {
                    specification = specification == null? Specification.where(InvoiceSpecification.userIdIs(criteria.getUserId()))
                    : specification.and(InvoiceSpecification.userIdIs(criteria.getUserId()));
                }

                if (criteria.getCustomerId() !=null) {
                    specification = specification == null? Specification.where(InvoiceSpecification.customerIdIs(criteria.getCustomerId()))
                    : specification.and(InvoiceSpecification.customerIdIs(criteria.getCustomerId()));
                }

                if (criteria.getAddressId() !=null ) {
                    specification = specification == null? Specification.where(InvoiceSpecification.addressIdIs(criteria.getAddressId()))
                    : specification.and(InvoiceSpecification.addressIdIs(criteria.getAddressId()));
                }

                if (criteria.getCompanyId() !=null ) {
                    specification = specification == null? Specification.where(InvoiceSpecification.companyIdIs(criteria.getCompanyId()))
                    : specification.and(InvoiceSpecification.companyIdIs(criteria.getCompanyId()));
                }

        if (criteria.getCustomerFullName() != null) {
            specification = specification == null
                    ? Specification.where(InvoiceSpecification.customerFullNameIs(criteria.getCustomerFullName()))
                    : specification.and(InvoiceSpecification.customerFullNameIs(criteria.getCustomerFullName()));
        }

        if (criteria.getFromDate() != null || criteria.getToDate() != null) {
            specification = specification == null
                    ? Specification.where(InvoiceSpecification.invoiceDateBetween(criteria.getFromDate(), criteria.getToDate()))
                    : specification.and(InvoiceSpecification.invoiceDateBetween(criteria.getFromDate(), criteria.getToDate()));
        }

//        if (criteria.getProductName() != null) {
//            specification = specification == null
//                    ? Specification.where(InvoiceSpecification.productContainIs(criteria.getProductName()))
//                    : specification.and(InvoiceSpecification.productContainIs(criteria.getProductName()));//       }

                Pageable pageable = PageRequest.of(page, size);

                return specification == null? invoiceRepository.findAll(pageable) : invoiceRepository.findAll(specification, pageable);
    }

    public Page<Invoice> read(Pageable pageable) {
        return invoiceRepository.findAll(pageable); // Utilise le repository pour renvoyer une page d'invoices
    }

    @Override
    public Page<Invoice> searchByStatus(InvoiceStatus status, Pageable pageable) {
        return invoiceRepository.findAllByStatus(status, pageable);
    }

    @Override
    public Page<Invoice> searchByCustomerNameAndReference(String customerName, String reference, Pageable pageable) {
        return invoiceRepository.findAllByCustomerNameAndReference(customerName, reference, pageable);
    }
}
