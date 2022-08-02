package com.example.demo.converter;

import com.example.demo.model.Contact;
import com.example.demo.model.dto.ContactDto;
import org.springframework.stereotype.Service;

@Service
public class ContactConverter {

    public static Contact convertContactDtoToContact(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setId(contactDto.getId());
        contact.setFirstname(contactDto.getFirstname());
        contact.setLastname(contactDto.getLastname());
        contact.setAddress(contactDto.getAddress());
        contact.setStatus(contactDto.getStatus());
        contact.setStatus(contactDto.getStatus());
        contact.setVat(contactDto.getVat());
        contact.setArchived(false);

        return contact;
    }

    public static ContactDto convertContactToContactDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setFirstname(contact.getFirstname());
        contactDto.setLastname(contact.getLastname());
        contactDto.setAddress(contact.getAddress());
        contactDto.setStatus(contact.getStatus());
        contactDto.setStatus(contact.getStatus());
        contactDto.setVat(contact.getVat());

        return contactDto;
    }


}
