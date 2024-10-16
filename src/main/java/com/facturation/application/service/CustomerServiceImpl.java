package com.facturation.application.service;

import com.facturation.application.entities.Customer;
import com.facturation.application.dao.CustomerRepository;
import com.facturation.application.entities.Invoice;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository  customerRepository;


    @Override
    @Transactional
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Page<Customer> read(Pageable pageable) {
        return customerRepository.findAll(pageable); // Utilise le repository pour renvoyer une page d'invoices
    }

    @Override
    public Customer update(Long id, Customer customer) {
        return customerRepository.findById(id)
                .map (c->
                {
            c.setFirstName(customer.getFirstName());
            c.setLastName(customer.getLastName());
            c.setCityCustomer(customer.getCityCustomer());
            c.setNeighborhoodCustomer(customer.getNeighborhoodCustomer());
            c.setPhoneNumberCustomer(customer.getPhoneNumberCustomer());
            c.setEmail(customer.getEmail());
            return customerRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Customer non trouver !"));
    }

    @Override
    public Customer delete(Long id) {
        return customerRepository.findById(id).map(customer -> {customer.setDeleted(true);
            return customerRepository.save(customer);}).orElseThrow(()->new RuntimeException("Customer non triuve!"));
    }


    @Override
    public Customer search(Long id) {
        return  customerRepository.findById(id).orElseThrow(()->new RuntimeException("customer non truve!"));
    }
}
