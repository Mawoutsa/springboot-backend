package com.facturation.application.DTO.invoices;

import com.facturation.application.DTO.companies.CompanyDTO;
import com.facturation.application.DTO.customers.CustomerDTO;
import com.facturation.application.DTO.products.ProductDTO; // Use ProductDTO, not CreateProductDTO
import com.facturation.application.entities.InvoiceStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
    private Long id; 

    @Schema(description = "reference de l'invoice")
    private String reference;

    @Schema(description = "Date de l'invoice")
    private Date invoiceDate;

    private InvoiceStatus status;

    private boolean deleted;

    @Schema(description = "Le client lier")
    private CustomerDTO customer;

    private CompanyDTO company;

    @Schema(description = "Liste des produits liés")
    private List<ProductDTO> products; 
}