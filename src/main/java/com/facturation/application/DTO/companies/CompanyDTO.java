package com.facturation.application.DTO.companies;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    @NotNull
    @Schema(description = "l'id de la company")
    private Long id;

    @NotNull
    @Schema(description = "le nom de l'entreprise")
    private String companyName;

    @NotNull
    @Schema(description = "le slogan de l'entreprise")
    private String companyTagline;

    @NotNull
    @Schema(description = "la boite postal de l'entreprise")
    private String companyPoBox;

    @NotNull
    @Schema(description = "l'email de l'entreprise")
    private String emailCompany;

    @NotNull
    @Schema(description = "le site web de l'entreprise")
    private String siteWebCompany;

    @NotNull
    @Schema(description = "la ville de l'entreprise")
    private String cityCompany;

    @NotNull
    @Schema(description = "le quartier de l'entreprise")
    private String neighborhoodCompany;

    @NotNull
    @Schema(description = "le numero de telephone de l'entreprise")
    private String phoneNumberCompany;

}
