package org.example.model;

public enum Algorithms {
    REGEX_PHONE_NUMBER ("Введите номер телефона (Формат ввода: \"+7ХХХХХХХХХХ\"): "),
    REGEX_NAME ("Введите Фамилию Имя Отчество (Формат ввода: \"Фамилия Имя Отчество\")");
    private final String description;

    Algorithms(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

}
