package com.facturation.application.service;

import com.facturation.application.dao.CompanyRepository;
import com.facturation.application.entities.Company;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Data
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private  ModelMapperService modelMapperService;

    @Override
    @Transactional
    public Company create(Company company) {
       return  companyRepository.save(company);
    }

    @Override
    @Transactional
    public List<Company> read() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional
    public Company update(String name, Company company) {
        return companyRepository.findByCompanyName(name)
                .map(company1 -> {
                    company1.setCompanyName(company.getCompanyName());
                    company1.setCompanyTagline(company.getCompanyTagline());
                    company1.setEmailCompany(company.getEmailCompany());
                    company1.setCityCompany(company.getCityCompany());
                    company1.setNeighborhoodCompany(company.getNeighborhoodCompany());
                    company1.setCompanyPoBox(company.getCompanyPoBox());
                    company1.setSiteWebCompany(company.getSiteWebCompany());
                    company1.setPhoneNumberCompany(company.getPhoneNumberCompany());
                    return companyRepository.save(company1);
                }).orElseThrow(() -> new RuntimeException("Company non trouvee !"));
    }

    @Override
    @Transactional
    public Company delete(String name) {
        return companyRepository.findByCompanyName(name).map(company -> {company.setDeleted(true);
            return companyRepository.save(company);}).orElseThrow(()->new RuntimeException("company non trouve!"));
    }

    @Override
    @Transactional
    public Company search(String name) {
        return  companyRepository.findByCompanyName(name).orElseThrow(()->new RuntimeException("company non truve!"));
    }
}
