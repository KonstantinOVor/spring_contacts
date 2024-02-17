package org.example;

import org.example.config.AppConfig;
import org.example.service.ContactService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ContactService contactService = context.getBean(ContactService.class);
        while (true) {
            System.out.println("1. Вывести контакты");
            System.out.println("2. Добавить контакт");
            System.out.println("3. Удалить контакт по email");
            System.out.println("4. Сохранить контакты в файле");
            System.out.println("5. Выйти" + "\n");
            System.out.print("Выберите действие: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                Integer choice = Integer.valueOf(reader.readLine());

                switch (choice) {
                    case 1:
                        contactService.findAll();
                        break;
                    case 2:
                        contactService.saveInMemory();
                        break;
                    case 3:
                        System.out.println("Введите email контакта: ");
                        String email = reader.readLine();
                        contactService.delete(email);
                        break;
                    case 4:
                        contactService.saveAllInFile();
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}