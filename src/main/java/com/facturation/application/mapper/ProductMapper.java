package com.facturation.application.mapper;

import com.facturation.application.DTO.products.CreateProductDTO;
import com.facturation.application.DTO.products.ProductDTO;
import com.facturation.application.DTO.products.UpdateProductDTO;
import com.facturation.application.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);

    Product fromDTO(CreateProductDTO dto);

    Product fromDTO(UpdateProductDTO dto);

}
