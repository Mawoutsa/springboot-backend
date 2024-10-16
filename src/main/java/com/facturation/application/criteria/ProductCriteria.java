package com.facturation.application.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCriteria {

    private String productReference;
    private String description;
    private Integer page;
    private Integer size;

    public ProductCriteria() {

    }
}
