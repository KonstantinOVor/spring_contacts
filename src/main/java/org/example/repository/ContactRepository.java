package org.example.repository;

import org.example.model.Contact;
import java.util.Set;

public interface ContactRepository {

    void saveContact();

    Set<Contact> getContacts();

    void deleteContact(String email);

}
