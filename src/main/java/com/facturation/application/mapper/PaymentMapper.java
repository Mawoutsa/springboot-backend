package com.facturation.application.mapper;

import com.facturation.application.DTO.payments.CreatePaymentDTO;
import com.facturation.application.DTO.payments.PaymentDTO;
import com.facturation.application.DTO.payments.UpdatePaymentDTO;
import com.facturation.application.entities.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDTO toDTO(Payment payment);

    Payment fromDTO(CreatePaymentDTO dto);

    Payment fromDTO(UpdatePaymentDTO dto);
}
