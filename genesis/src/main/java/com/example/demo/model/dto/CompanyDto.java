package com.example.demo.model.dto;

import com.example.demo.model.Address;
import com.example.demo.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private Long id;
    private String name;
    private Address address;
    private String vat;
    private Set<Contact> contacts;

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Long id) {
        contacts.removeIf(c -> c.getId().equals(id));
    }



}
