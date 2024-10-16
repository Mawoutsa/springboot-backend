package com.facturation.application.DTO.products;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotNull
    @Schema(description = "l'id du product")
    private Long id;

    @NotNull
    @Schema(description = "reference du  product")
    private String productReference;

    @NotNull
    @Schema(description = "description du product")
    private String description;

    @NotNull
    @Schema(description = "prix unitaire du product")
    private Double unitPrice;

    @Transient
    private Double totalAmount;

    @NotNull
    @Schema(description = "quantite procduct")
    private Double quantity;

    // Prix total calculé
    public Double getTotalAmount() {
        return unitPrice * quantity;
    }


}
