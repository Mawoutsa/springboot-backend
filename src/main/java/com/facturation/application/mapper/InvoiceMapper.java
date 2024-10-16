package com.facturation.application.mapper;

import com.facturation.application.DTO.invoices.CreateInvoiceDTO;
import com.facturation.application.DTO.invoices.InvoiceDTO;
import com.facturation.application.DTO.invoices.UpdateInvoiceDTO;
import com.facturation.application.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    InvoiceDTO toDTO(Invoice invoice);

    Invoice fromDTO(CreateInvoiceDTO dto);

    Invoice fromDTO(UpdateInvoiceDTO dto);
}
