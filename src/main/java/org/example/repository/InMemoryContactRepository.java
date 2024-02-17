package org.example.repository;

import org.example.model.Contact;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
public class InMemoryContactRepository {
    private String patternName = "^([А-ЯЁ][а-яё]+\\s){2}[А-ЯЁ][а-яё]+";
    private String patternEmail = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
    private String patternNumber = "\\+\\d{11}";
    private Set<Contact> contacts = new HashSet<>();


    public void saveContact() {
        Contact contact = new Contact();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            changeOfName(contact, reader);
            changeOfPhoneNumber(contact, reader);
            changeOfEmail(contact, reader);
            contacts.add(contact);
        } catch (IOException e) {
            System.out.println("Ошибка чтения пользовательского ввода!");
        }
    }

    private void changeOfName(Contact contact,BufferedReader reader) throws IOException {
        System.out.println("Введите Фамилию Имя Отчество (Формат ввода: \"Фамилия Имя Отчество\"):");
        String fullName = reader.readLine();
        if (fullName.matches(patternName)) {
            contact.setFullName(fullName);
        } else {
            System.out.println("Фамилия Имя Отчество введены некорректно! ");
            changeOfName(contact, reader);
        }
    }

    private void changeOfEmail(Contact contact,BufferedReader reader) throws IOException {
        System.out.println("Введите email (Формат ввода: \"username@example.com\"):");
        String email = reader.readLine();
        if (email.matches(patternEmail)) {
            contact.setEmail(email);
        } else {
            System.out.println("Email введен некорректно!");
            changeOfEmail(contact, reader);
        }
    }

    private void changeOfPhoneNumber(Contact contact,BufferedReader reader) throws IOException {
        System.out.println("Введите номер телефона (Формат ввода: \"+71234567890\"):");
        String phoneNumber = reader.readLine();
        if (phoneNumber.matches(patternNumber)) {
            contact.setPhoneNumber(phoneNumber);
        } else {
            System.out.println("Номер телефона введен некорректно!");
            changeOfPhoneNumber(contact, reader);
        }
    }

    public Set<Contact> getContacts() {
        return contacts;
    }
}
