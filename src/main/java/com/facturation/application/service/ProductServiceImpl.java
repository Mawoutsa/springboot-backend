package com.facturation.application.service;

import com.facturation.application.criteria.ProductCriteria;
import com.facturation.application.criteria.ProductSpecification;
import com.facturation.application.dao.ProductRepository;
import com.facturation.application.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productDao;


    @Override
    public Product create(Product product) {
        return productDao.save(product);
    }

    @Override
    public Page<Product> read(Pageable pageable) {
        return productDao.findAll(pageable); // Utilise le repository pour renvoyer une page d'invoices
    }

    @Override
    public Product update(Long id, Product product) {
        return productDao.findById(id)
                .map(p->
                {
            p.setProductReference(product.getProductReference());
            p.setDescription(product.getDescription());
            p.setUnitPrice(product.getUnitPrice());
            p.setQuantity(product.getQuantity());
            p.setTotalAmount(product.getTotalAmount());
            return productDao.save(p);
        }).orElseThrow(() -> new RuntimeException("Product non trouver !"));
    }

    @Override
    public Product delete(Long id) {
        return productDao.findById(id).map(product -> {product.setDeleted(true);
        return productDao.save(product);}).orElseThrow(()->new RuntimeException("Produit non triuve!"));
    }

    @Override
    public Page<Product> search(ProductCriteria criteria, int page, int size){
        Specification<Product> specification = null;


        if (criteria.getProductReference() !=null && !criteria.getProductReference().isEmpty()) {
            specification = Specification.where(ProductSpecification.referenceIs(criteria.getProductReference()));
        }
        if (criteria.getDescription() !=null && !criteria.getDescription().isEmpty()) {
            specification = specification == null? Specification.where(ProductSpecification.descriptionIs(criteria.getDescription()))
                    : specification.and(ProductSpecification.descriptionIs(criteria.getDescription()));
        }

        Pageable pageable = PageRequest.of(page, size);

        return specification == null? productDao.findAll(pageable) : productDao.findAll(specification,pageable);
    }

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(productDao.findById(id).orElseThrow(() -> new RuntimeException("Produit non trouve!")));
    }
}
