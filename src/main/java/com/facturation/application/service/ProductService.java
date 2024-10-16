package com.facturation.application.service;

import com.facturation.application.criteria.ProductCriteria;
import com.facturation.application.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface ProductService {

    Product create(Product product);

    Page<Product> read(Pageable pageable);

    Product update(Long id, Product product);

    Product delete(Long id);

    Page<Product> search(ProductCriteria criteria, int page, int size);


    Optional<Product> findById(Long id);
}
