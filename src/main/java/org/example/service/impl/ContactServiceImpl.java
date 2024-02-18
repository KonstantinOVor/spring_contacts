package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.Contact;
import org.example.repository.FileContactRepository;
import org.example.repository.InMemoryContactRepository;
import org.example.service.ContactService;
import org.springframework.stereotype.Component;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class ContactServiceImpl implements ContactService {
    private final InMemoryContactRepository inMemoryContactRepository;
    private final FileContactRepository fileContactRepository;


    @Override
    public void saveInMemory() {
        inMemoryContactRepository.saveContact();
    }

    @Override
    public void delete(String email) {
        fileContactRepository.deleteContact(email);
        inMemoryContactRepository.getContacts().removeIf(contact -> contact.getEmail().equals(email));
    }
    @Override
    public void findAll() {
        Set<Contact> contacts = fileContactRepository.readContactsFromFile();
        StringBuilder sb = new StringBuilder();
        for (Contact contact : contacts) {
           sb.append(contact.toString()).append(System.lineSeparator());
        }
        System.out.println(sb);
    }
    @Override
    public void saveAllInFile() {
        Set<Contact> contacts = inMemoryContactRepository.getContacts();
        fileContactRepository.saveContact(contacts);
    }
}

