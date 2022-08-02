package com.example.demo.controller;

import com.example.demo.exception.ContactException;
import com.example.demo.model.dto.ContactDto;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<ContactDto> getContacts() {
        return contactService.findAll();
    }

    @GetMapping("/{id}")
    public ContactDto getContactById(@PathVariable Long id) {
        try {
            return contactService.findById(id);
        } catch (ContactException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found", e);
        }
    }

    @PostMapping
    public ContactDto save(@RequestBody ContactDto contactDto) throws ContactException {
        return contactService.save(contactDto);
    }

    @PutMapping
    public ContactDto update(@RequestBody ContactDto contactDto) throws ContactException {
        return contactService.update(contactDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        contactService.delete(id);
    }

    @DeleteMapping("/archive/{id}")
    public void archive(@PathVariable Long id) {
        try {
            contactService.archive(id);
        } catch (ContactException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact to archive not found", e);
        }
    }
}
