package com.facturation.application.DTO.invoices;

import com.facturation.application.DTO.companies.CompanyDTO;
import com.facturation.application.DTO.companies.CreateCompanyDTO;
import com.facturation.application.DTO.customers.CreateCustomerDTO;
import com.facturation.application.DTO.customers.CustomerDTO;
import com.facturation.application.DTO.products.CreateProductDTO;
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
public  class UpdateInvoiceDTO {

    @NotNull
    @Schema(description = "description de invoice")
    private String reference;

    @Schema(description = "Date de l'invoice")
    private Date invoiceDate;

    private boolean deleted;

    @NotNull
    @Schema(description = "Le client lier")
    private CreateCustomerDTO customer;

    @NotNull
    private CreateCompanyDTO company;

    @NotNull
    @Schema(description = "Liste des produits liés")
    private List<CreateProductDTO> products;

}
