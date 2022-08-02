package com.example.demo.converter;

import com.example.demo.model.Company;
import com.example.demo.model.dto.CompanyDto;
import org.springframework.stereotype.Service;

@Service
public class CompanyConverter {

    public static Company convertCompanyDtoToCompany(CompanyDto companyDto) {
        Company company = new Company();

        company.setId(companyDto.getId());
        company.setName(companyDto.getName());
        company.setAddress(companyDto.getAddress());
        company.setVat(companyDto.getVat());
        company.setContacts(companyDto.getContacts());
        company.setArchived(false);

        return company;
    }

    public static CompanyDto convertCompanyToCompanyDto(Company company) {
        CompanyDto companyDto = new CompanyDto();

        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setAddress(company.getAddress());
        companyDto.setVat(company.getVat());
        companyDto.setContacts(company.getContacts());

        return companyDto;
    }
}
