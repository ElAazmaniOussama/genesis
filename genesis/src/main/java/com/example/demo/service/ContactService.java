package com.example.demo.service;

import com.example.demo.converter.ContactConverter;
import com.example.demo.exception.ContactException;
import com.example.demo.model.Contact;
import com.example.demo.model.Status;
import com.example.demo.model.dto.ContactDto;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactDto> findAll() {
        List<Contact> contacts = contactRepository.findByArchivedFalse();

        return contacts.stream().map(ContactConverter::convertContactToContactDto).collect(Collectors.toList());
    }

    public ContactDto findById(Long id) throws ContactException {
        Optional<Contact> contact = contactRepository.findByIdAndArchivedFalse(id);

        if (contact.isPresent()) {
            return ContactConverter.convertContactToContactDto(contact.get());
        }
        throw new ContactException("Contact with id " + id + " was not found");
    }

    public ContactDto save(ContactDto contactDto) throws ContactException {
        contactDto.setId(null);
        contactDto.getAddress().setId(null);

        if (contactDto.getStatus() == Status.FREELANCE && isBlankString(contactDto.getVat())) {
            throw new ContactException("Freelance contact must have a VAT number");
        }

        Contact contact = contactRepository.save(ContactConverter.convertContactDtoToContact(contactDto));
        return ContactConverter.convertContactToContactDto(contact);
    }

    public ContactDto update(ContactDto contactDto) throws ContactException {
        if (contactDto.getId() == null) {
            throw new ContactException("Contact id was not provided");
        }
        Contact contact = contactRepository.save(ContactConverter.convertContactDtoToContact(contactDto));
        return ContactConverter.convertContactToContactDto(contact);
    }

    public void delete(Long id) {
        contactRepository.deleteById(id);
    }

    public void archive(Long id) throws ContactException {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if(optionalContact.isEmpty()) {
            throw new ContactException("Contact with id " + id + " was not found");
        }
        Contact contact = optionalContact.get();
        contact.setArchived(true);
        contactRepository.save(contact);
    }

    private boolean isBlankString(String string) {
        return string == null || string.isBlank();
    }


}
