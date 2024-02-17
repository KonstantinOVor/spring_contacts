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

    public void delete(String email) {
        inMemoryContactRepository.getContacts().removeIf(contact -> contact.getEmail().equals(email));
    }

    public void findAll() {
        Set<Contact> contacts = inMemoryContactRepository.getContacts();
        StringBuilder sb = new StringBuilder();
        for (Contact contact : contacts) {
           sb.append(contact.toString()).append("\n");
        }
        System.out.println(sb);
    }

    public void saveAllInFile() {
        Set<Contact> contacts = inMemoryContactRepository.getContacts();
        fileContactRepository.saveContact(contacts);
    }
}

