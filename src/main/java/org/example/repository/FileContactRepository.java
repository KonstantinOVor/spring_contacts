package org.example.repository;


import lombok.RequiredArgsConstructor;
import org.example.model.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class FileContactRepository  {

    private final ResourceLoader resourceLoader;
    @Value("${contacts.file.path}")
    private  String filePath;

    public void saveContact(Set<Contact> contacts) {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + filePath);
            File file = resource.getFile();
            System.out.println("Путь к файлу: " + file.getAbsolutePath());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Contact contact : contacts) {
                    writer.write(contact.getFullName() + "; " + contact.getPhoneNumber() + "; " + contact.getEmail());
                    writer.newLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Ошибка чтения пользовательского ввода");
            throw new RuntimeException(e);
        }
    }
}
