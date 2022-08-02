package com.example.demo.controller;

import com.example.demo.exception.CompanyException;
import com.example.demo.exception.ContactException;
import com.example.demo.model.dto.CompanyDto;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyDto> getCompany() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyDto getCompany(@PathVariable Long id) throws CompanyException {
        return companyService.findById(id);
    }

    @GetMapping("/vat/{vat}")
    public CompanyDto getCompanyByVat(@PathVariable String vat) throws CompanyException {
        return companyService.findByVat(vat);
    }

    @PostMapping
    public CompanyDto save(@RequestBody CompanyDto companyDto) {
        return companyService.save(companyDto);
    }

    @PostMapping("/contact/add/{companyId}/{contactId}")
    public void addContact(@PathVariable Long companyId, @PathVariable Long contactId) throws ContactException, CompanyException {
        companyService.addContact(companyId, contactId);
    }

    @DeleteMapping("/contact/remove/{companyId}/{contactId}")
    public void removeContact(@PathVariable Long companyId, @PathVariable Long contactId) throws CompanyException {
        companyService.removeContact(companyId, contactId);
    }

    @PutMapping
    public CompanyDto update(@RequestBody CompanyDto companyDto) throws CompanyException {
        return companyService.update(companyDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }

    @DeleteMapping("/archive/{id}")
    public void archive(@PathVariable Long id) {
        try {
            companyService.archive(id);
        } catch (CompanyException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company to archive not found", e);
        }
    }


}
