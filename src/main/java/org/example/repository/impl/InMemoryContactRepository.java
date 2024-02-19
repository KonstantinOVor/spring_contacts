package org.example.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.model.info.Algorithms;
import org.example.model.Contact;
import org.example.model.info.NegativeResponse;
import org.example.repository.ContactRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
@Primary
public class InMemoryContactRepository implements ContactRepository {
    private String patternName = "^([А-ЯЁ][а-яё]+\\s){2}[А-ЯЁ][а-яё]+";
    private String patternEmail = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
    private String patternNumber = "\\+\\d{11}";
    private Set<Contact> contacts = new HashSet<>();

    @Override
    public void saveContact() {
        Contact contact = new Contact();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            changeOfName(contact, reader);
            changeOfPhoneNumber(contact, reader);
            changeOfEmail(contact, reader);
            contacts.add(contact);
        } catch (IOException e) {
            log.error(NegativeResponse.NEGATIVE_RESPONSE_FILE_READ.getDescription());
        }
    }

    private void changeOfName(Contact contact,BufferedReader reader) throws IOException {
        System.out.println(Algorithms.REGEX_NAME.getDescription());
        String fullName = reader.readLine();
        if (fullName.matches(patternName)) {
            contact.setFullName(fullName);
        } else {
            log.error(NegativeResponse.NEGATIVE_RESPONSE_NAME.getDescription());
            changeOfName(contact, reader);
        }
    }

    private void changeOfEmail(Contact contact,BufferedReader reader) throws IOException {
        System.out.println(Algorithms.REGEX_EMAIL.getDescription());
        String email = reader.readLine();
        if (email.matches(patternEmail)) {
            contact.setEmail(email);
        } else {
            log.error(NegativeResponse.NEGATIVE_RESPONSE_EMAIL.getDescription());
            changeOfEmail(contact, reader);
        }
    }

    private void changeOfPhoneNumber(Contact contact,BufferedReader reader) throws IOException {
        System.out.println(Algorithms.REGEX_PHONE_NUMBER.getDescription());
        String phoneNumber = reader.readLine();
        if (phoneNumber.matches(patternNumber)) {
            contact.setPhoneNumber(phoneNumber);
        } else {
            log.error(NegativeResponse.NEGATIVE_RESPONSE_PHONE_NUMBER.getDescription());
            changeOfPhoneNumber(contact, reader);
        }
    }
    @Override
    public Set<Contact> getContacts() {

        return contacts;
    }
    @Override
    public void deleteContact(String email) {
        contacts.removeIf(contact -> contact.getEmail().equals(email));
    }
}
