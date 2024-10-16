package com.facturation.application.controllers;


import com.facturation.application.DTO.companies.CompanyDTO;
import com.facturation.application.DTO.companies.CreateCompanyDTO;
import com.facturation.application.DTO.companies.UpdateCompanyDTO;
import com.facturation.application.mapper.CompanyMapper;
import com.facturation.application.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyMapper companyMapper;

    @PostMapping
    @Operation(summary = "Créer une nouvelle companies")
    public CompanyDTO create(@RequestBody CreateCompanyDTO createCompanyDTO) {
        var company = companyService.create(companyMapper.fromDTO(createCompanyDTO));
        return companyMapper.toDTO(company);
    }
    @GetMapping
    @Operation(summary = "Lister toutes les company")
    public List<CompanyDTO> read(){
        return  companyService.read().stream().map(companyMapper::toDTO).collect(Collectors.toList());

    }

    @PutMapping("/{name}")
    @Operation(summary = "Mettre à jour une company par son nom")
    public CompanyDTO update(@PathVariable String companyName, @RequestBody UpdateCompanyDTO updateCompanyDTO){
        return  companyMapper.toDTO(companyService.update(companyName,companyMapper.fromDTO(updateCompanyDTO)));

    }

    @DeleteMapping("/{name}")
    @Operation(summary = "Supprimer une company par son nom")
    public void delete (@PathVariable String companyName){
        companyService.delete(companyName);

    }

    @GetMapping("/{name}")
    @Operation(summary = "Rechercher une company par son nom")
    public void search (@PathVariable String companyName){
        companyService.search(companyName);
    }
}

