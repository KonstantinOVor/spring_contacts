package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.config.AppConfig;
import org.example.model.info.Algorithms;
import org.example.model.info.NegativeResponse;
import org.example.service.ContactService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class Main {
    private static String oneOption = "1. Вывести контакты";
    private static String twoOption = "2. Добавить контакт";
    private static String threeOption = "3. Удалить контакт по email";
    private static String fourOption = "4. Сохранить контакты в файле";
    private static String exitOption = "5. Выход";
    private static String choose = "Выберите действие: ";


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ContactService contactService = context.getBean(ContactService.class);
        while (true) {
            System.out.println(oneOption);
            System.out.println(twoOption);
            System.out.println(threeOption);
            System.out.println(fourOption);
            System.out.println(exitOption + "\n");
            System.out.print(choose);
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
                        System.out.println(Algorithms.EMAIL.getDescription());
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
                        log.error(NegativeResponse.NEGATIVE_RESPONSE_CHOICE.getDescription());
                        break;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}