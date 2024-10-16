package com.facturation.application.service;

import com.facturation.application.DTO.payments.UpdatePaymentDTO;
import com.facturation.application.entities.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    Payment create(Payment payment);

    List<Payment> read();

    Payment update(Long id, Payment payment);

    Payment delete(Long id);

    Payment search(Long id);

}
