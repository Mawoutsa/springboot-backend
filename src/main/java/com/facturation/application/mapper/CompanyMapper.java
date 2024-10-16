package com.facturation.application.mapper;

import com.facturation.application.DTO.companies.CompanyDTO;
import com.facturation.application.DTO.companies.CreateCompanyDTO;
import com.facturation.application.DTO.companies.UpdateCompanyDTO;
import com.facturation.application.entities.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO toDTO(Company company);

    Company fromDTO(CreateCompanyDTO dto);

    Company fromDTO(UpdateCompanyDTO dto);

}
