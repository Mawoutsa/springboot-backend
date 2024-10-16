package com.facturation.application.service;

import com.facturation.application.entities.Customer;
import com.facturation.application.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    Customer create(Customer customer);

    Page<Customer> read(Pageable pageable);

        Customer update(Long id, Customer customer);

        Customer delete(Long id);

        Customer search(Long id);
}
