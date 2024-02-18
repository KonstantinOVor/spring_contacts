package org.example.repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Contact;
import org.example.model.info.Algorithms;
import org.example.model.info.NegativeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileContactRepository  {
    private final ResourceLoader resourceLoader;
    @Value("${contacts.file.path}")
    private  String filePath;
    private String separator = "; ";

    public void saveContact(Set<Contact> contacts) {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + filePath);
            File file = resource.getFile();
            System.out.println(Algorithms.PATH_TO_FILE.getDescription() + file.getAbsolutePath());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Contact contact : contacts) {
                    writer.write(contact.getFullName() + separator + contact.getPhoneNumber()
                            + separator + contact.getEmail());
                    writer.newLine();
                }
            }
        } catch (FileNotFoundException e) {
            log.error(NegativeResponse.NEGATIVE_RESPONSE_FILE.getDescription());
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error(NegativeResponse.NEGATIVE_RESPONSE_USER_INPUT.getDescription());
            throw new RuntimeException(e);
        }
    }
    public Set<Contact> readContactsFromFile() {
        Set<Contact> contacts = new HashSet<>();
        try {
            Resource resource = resourceLoader.getResource("classpath:" + filePath);
            File file = resource.getFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] contactData = line.split(separator);
                    Contact contact = new Contact(contactData[0], contactData[1], contactData[2]);
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            log.error(NegativeResponse.NEGATIVE_RESPONSE_FILE_READ.getDescription(), filePath);
            throw new RuntimeException(e);
        }
        return contacts;
    }

    public void deleteContact(String email) {
        Set<Contact> contacts = readContactsFromFile();
        for (Contact contact : contacts) {
            if (contact.getEmail().equals(email)) {
                contacts.remove(contact);
                saveContact(contacts);
                break;
            } else {
                log.error(NegativeResponse.NEGATIVE_RESPONSE_CONTACT.getDescription());
            }
        }
    }
}
