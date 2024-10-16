package com.facturation.application.service;

import com.facturation.application.dao.InvoiceRepository;
import com.facturation.application.dao.PaymentRepository;
import com.facturation.application.entities.Invoice;
import com.facturation.application.entities.Payment;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    @Transactional
    public Payment create(Payment payment) {

        Invoice invoice = invoiceRepository.findById(payment.getInvoice().getId())
                .orElseThrow(() -> new EntityNotFoundException("invoice not found"));

        payment.setInvoice(invoice);
        return paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public List<Payment> read() {
        return paymentRepository.findAll();
    }

    @Override
    @Transactional
    public Payment update(Long id, Payment payment) {
        return paymentRepository.findById(id)
                .map(payment1 ->
                {
                    payment1.setPaymentDate(payment.getPaymentDate());
                    payment1.setCollectedAmount(payment.getCollectedAmount());
                    payment1.setModePaiement(payment.getModePaiement());
                    return paymentRepository.save(payment1);
                }).orElseThrow(() -> new RuntimeException("payment not found !"));
    }

    @Override
    @Transactional
    public Payment delete(Long id) {
        return paymentRepository.findById(id).map(payment -> {
            payment.setDeleted(true);
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new RuntimeException("payment not found!"));
    }

        @Override
        @Transactional
    public Payment search(Long id) {
            return  paymentRepository.findById(id).orElseThrow(()->new RuntimeException("payment not found!"));

        }

}
