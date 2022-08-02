package com.example.demo.service;

import com.example.demo.converter.CompanyConverter;
import com.example.demo.converter.ContactConverter;
import com.example.demo.exception.CompanyException;
import com.example.demo.exception.ContactException;
import com.example.demo.model.Company;
import com.example.demo.model.Contact;
import com.example.demo.model.dto.CompanyDto;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ContactService contactService;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, ContactService contactService) {
        this.companyRepository = companyRepository;
        this.contactService = contactService;
    }

    public List<CompanyDto> findAll() {
        List<Company> companies = companyRepository.findByArchivedFalse();

        return companies.stream().map(CompanyConverter::convertCompanyToCompanyDto).collect(Collectors.toList());

    }

    public CompanyDto findById(Long id) throws CompanyException {
        Optional<Company> company = companyRepository.findByIdAndArchivedFalse(id);

        if (company.isPresent()) {
            return CompanyConverter.convertCompanyToCompanyDto(company.get());
        }
        throw new CompanyException("Company with id " + id + " was not found");
    }

    public CompanyDto findByVat(String vat) throws CompanyException {
        Optional<Company> company = companyRepository.findByVatAndArchivedFalse(vat);

        if (company.isPresent()) {
            return CompanyConverter.convertCompanyToCompanyDto(company.get());
        }
        throw new CompanyException("Company with vat " + vat + " was not found");
    }

    public CompanyDto save(CompanyDto companyDto) {
        companyDto.setId(null);
        companyDto.getAddress().setId(null);

        Company company = companyRepository.save(CompanyConverter.convertCompanyDtoToCompany(companyDto));
        return CompanyConverter.convertCompanyToCompanyDto(company);
    }

    public CompanyDto update(CompanyDto companyDto) throws CompanyException {
        if (companyDto.getId() == null) {
            throw new CompanyException("Company id was not provided");
        }
        CompanyDto loadedCompany = findById(companyDto.getId());
        companyDto.setContacts(loadedCompany.getContacts());

        Company company = companyRepository.save(CompanyConverter.convertCompanyDtoToCompany(companyDto));
        return CompanyConverter.convertCompanyToCompanyDto(company);
    }

    public void addContact(Long companyId, Long contactId) throws CompanyException, ContactException {
        CompanyDto companyDto = findById(companyId);
        Contact contact = ContactConverter.convertContactDtoToContact(contactService.findById(contactId));
        contact.setArchived(false);
        companyDto.addContact(contact);


        companyRepository.save(CompanyConverter.convertCompanyDtoToCompany(companyDto));
    }

    public void removeContact(Long id, Long contactId) throws CompanyException {
        CompanyDto companyDto = findById(id);
        companyDto.removeContact(contactId);

        companyRepository.save(CompanyConverter.convertCompanyDtoToCompany(companyDto));
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }

    public void archive(Long id) throws CompanyException {
        Optional<Company> optionalContact = companyRepository.findById(id);
        if (optionalContact.isEmpty()) {
            throw new CompanyException("Company with id " + id + " was not found");
        }
        Company company = optionalContact.get();
        company.setArchived(true);
        companyRepository.save(company);
    }
}
