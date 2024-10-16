package com.facturation.application.controllers;


import com.facturation.application.DTO.invoices.InvoiceDTO;
import com.facturation.application.DTO.invoices.UpdateInvoiceDTO;
import com.facturation.application.DTO.payments.CreatePaymentDTO;
import com.facturation.application.DTO.payments.PaymentDTO;
import com.facturation.application.DTO.payments.UpdatePaymentDTO;
import com.facturation.application.entities.Customer;
import com.facturation.application.entities.Payment;
import com.facturation.application.mapper.PaymentMapper;
import com.facturation.application.service.ModelMapperService;
import com.facturation.application.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    private final PaymentMapper paymentMapper;

    @PostMapping
    @Operation(summary = "Créer un nouveau payment")
    public PaymentDTO create(@RequestBody CreatePaymentDTO createPaymentDTO){
        Payment payment = paymentMapper.fromDTO(createPaymentDTO);
        return paymentMapper.toDTO(paymentService.create(payment));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les payments")
    public List<PaymentDTO> read(){
        return  paymentService.read().stream().map(paymentMapper::toDTO).collect(Collectors.toList());

    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un payment par son id")
    public PaymentDTO update(@PathVariable Long id, @RequestBody UpdatePaymentDTO updatePaymentDTO){
        return  paymentMapper.toDTO(paymentService.update(id,paymentMapper.fromDTO(updatePaymentDTO)));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un payment par son id")
    public void delete(@PathVariable Long id){
        paymentService.delete(id);
    }



    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un payment par son id")
    public void search (@PathVariable Long id){
        paymentService.search(id);
    }
}
