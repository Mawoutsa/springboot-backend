package com.facturation.application.mapper;

import com.facturation.application.DTO.customers.CreateCustomerDTO;
import com.facturation.application.DTO.customers.CustomerDTO;
import com.facturation.application.DTO.customers.UpdateCustomerDTO;
import com.facturation.application.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);

    Customer fromDTO(CreateCustomerDTO dto);

    Customer fromDTO(UpdateCustomerDTO dto);
}
