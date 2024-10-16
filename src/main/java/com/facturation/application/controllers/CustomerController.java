package com.facturation.application.controllers;

import com.facturation.application.DTO.customers.CreateCustomerDTO;
import com.facturation.application.DTO.customers.CustomerDTO;
import com.facturation.application.DTO.customers.UpdateCustomerDTO;
import com.facturation.application.DTO.invoices.InvoiceDTO;
import com.facturation.application.entities.Customer;
import com.facturation.application.entities.Invoice;
import com.facturation.application.mapper.CustomerMapper;
import com.facturation.application.service.CustomerService;
import com.facturation.application.service.ModelMapperService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    @PostMapping
    @Operation(summary = "Créer un nouveau customer")
    public CustomerDTO create(@RequestBody CreateCustomerDTO createCustomerDTO){
        Customer customer = customerMapper.fromDTO(createCustomerDTO);
      return customerMapper.toDTO(customerService.create(customer));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les customers avec pagination")
    public Page<CustomerDTO> read(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Page<Customer> customerPage = customerService.read(PageRequest.of(page, size));
        return customerPage.map(customer -> customerMapper.toDTO(customer));
    }

        @PutMapping("/{id}")
        public CustomerDTO update(@PathVariable Long id, @RequestBody UpdateCustomerDTO updateCustomerDTO){
        Customer customer = customerMapper.fromDTO(updateCustomerDTO);
            return  customerMapper.toDTO(customerService.update(id, customer));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un customer par son id")
        public void delete (@PathVariable Long id){
            customerService.delete(id);

    }

        @GetMapping("/{id}")
    @Operation(summary = "Rechercher un customer par son id")
        public void search (@PathVariable Long id){
            customerService.search(id);
        }
}
