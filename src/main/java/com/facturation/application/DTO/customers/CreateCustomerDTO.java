package com.facturation.application.DTO.customers;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerDTO {
    @NotNull
    @Schema(description = "le nom du customer")
    private  String firstName;

    @NotNull
    private  String lastName;

    @NotNull
    private String cityCustomer;

    @NotNull
    private String neighborhoodCustomer;

    @NotNull
    private String phoneNumberCustomer;

    @NotNull
    private  String email;
}
