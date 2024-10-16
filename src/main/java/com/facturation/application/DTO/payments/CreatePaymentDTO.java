package com.facturation.application.DTO.payments;

import com.facturation.application.DTO.invoices.InvoiceDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentDTO {

    @NotNull
    private Date paymentDate;

    @NotNull
    private  double collectedAmount;

    @NotNull
    private String modePaiement;


    @NotNull
    private InvoiceDTO invoice;

}
