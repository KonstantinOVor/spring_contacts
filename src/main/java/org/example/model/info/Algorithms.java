package org.example.model.info;

public enum Algorithms {
    REGEX_PHONE_NUMBER ("Введите номер телефона (Формат ввода: \"+7ХХХХХХХХХХ\"): "),
    REGEX_NAME ("Введите Фамилию Имя Отчество (Формат ввода: \"Фамилия Имя Отчество\"): "),
    REGEX_EMAIL("Введите email (Формат ввода: \"username@example.com\"): "),
    EMAIL ("Введите email контакта: "),
    PATH_TO_FILE("Путь к файлу: ");
    private final String description;

    Algorithms(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
