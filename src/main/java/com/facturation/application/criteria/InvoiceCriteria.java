package com.facturation.application.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class InvoiceCriteria {

    private String reference;
    private Long userId;
    private Long customerId;
    private Long addressId;
    private Long companyId;
    private Integer page;
    private Integer size;
    private String customerFullName;
    private Date fromDate;
    private Date toDate;
    private String productName;
    public InvoiceCriteria() {

    }
}
