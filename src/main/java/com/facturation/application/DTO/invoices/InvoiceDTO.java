package com.facturation.application.DTO.invoices;

import com.facturation.application.DTO.companies.CompanyDTO;
import com.facturation.application.DTO.customers.CustomerDTO;
import com.facturation.application.DTO.products.CreateProductDTO;
import com.facturation.application.entities.InvoiceStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public  class InvoiceDTO {
    @NotNull
    @Schema(description = "Id de l'invoice")
    private  Long id;

    @NotNull
    @Schema(description = "reference de l'invoice")
    private String reference;

    @Schema(description = "Date de l'invoice")
    private Date invoiceDate;

    private InvoiceStatus status; // Ajouter le statut

    private boolean deleted;

    @NotNull
    @Schema(description = "Le client lier")
    private CustomerDTO customer;

    @NotNull
    private CompanyDTO company;

    @NotNull
    @Schema(description = "Liste des produits liés")
    private List<CreateProductDTO> products;

}