package com.facturation.application.controllers;

import com.facturation.application.DTO.invoices.InvoiceDTO;
import com.facturation.application.DTO.products.CreateProductDTO;
import com.facturation.application.DTO.products.ProductDTO;
import com.facturation.application.DTO.products.UpdateProductDTO;
import com.facturation.application.criteria.ProductCriteria;
import com.facturation.application.entities.Invoice;
import com.facturation.application.entities.Product;
import com.facturation.application.mapper.ProductMapper;
import com.facturation.application.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
@Tag(name = "Product Controller", description = "API pour les opérations sur les products")
public class ProductController {

    private final ProductService productService;


    private final ProductMapper productMapper;

    @PostMapping
    @Operation(summary = "Créer un nouveau produit")
    public ProductDTO create(@RequestBody CreateProductDTO createProductDTO) {
        return productMapper.toDTO(productService.create(productMapper.fromDTO(createProductDTO)));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les products avec pagination")
    public Page<ProductDTO> read(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Page<Product> productPage = productService.read(PageRequest.of(page, size));
        return productPage.map(product -> productMapper.toDTO(product));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un product par son id")
    public ProductDTO update(@PathVariable Long id, @RequestBody UpdateProductDTO updateInvoiceDTO){
        return  productMapper.toDTO(productService.update(id,productMapper.fromDTO(updateInvoiceDTO)));

    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un product par son id")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }


    @PostMapping("/{search}")
    @Operation(summary = "Rechercher un product avec reference et/ou description avec pagination")
    public List<ProductDTO> searchProducts(
            @RequestParam(required = false) String productReference,
            @RequestParam(name = "query", required = false) String searchTerm,
            @RequestParam(defaultValue = "0" ) int page,
            @RequestParam(defaultValue = "100") int size){

        ProductCriteria criteria = new ProductCriteria();
        criteria.setDescription(searchTerm);
        criteria.setProductReference(productReference);

        Page<Product> productPage = productService.search(criteria, page, size);
        return productPage.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

}

