package com.facturation.application.service;

import com.facturation.application.entities.Company;

import java.util.List;

public interface CompanyService {

    Company create(Company company);

    List<Company> read();

    Company update(String name, Company company);

    Company delete(String name);

    Company search(String name);
}
