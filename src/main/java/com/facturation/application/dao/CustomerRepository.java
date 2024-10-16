package com.facturation.application.dao;

import com.facturation.application.entities.Customer;
import com.facturation.application.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Page<Customer> findAll(Pageable pageable);
}
