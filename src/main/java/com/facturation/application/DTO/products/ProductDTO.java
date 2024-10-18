package com.facturation.application.DTO.products;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id; // No need for @NotNull here, as it might be null for new products

    @NotNull
    @Schema(description = "reference du  product")
    private String productReference;

    @NotNull
    @Schema(description = "description du product")
    private String description;

    @NotNull
    @Schema(description = "prix unitaire du product")
    private BigDecimal unitPrice;

    @NotNull
    @Schema(description = "quantite procduct")
    private BigDecimal quantity; 

    private BigDecimal totalAmount; 

    // Prix total calculé
    public BigDecimal getTotalAmount() {
        if (unitPrice != null && quantity != null) {
            return unitPrice.multiply(quantity);
        } else {
            return BigDecimal.ZERO; 
        }
    }
}