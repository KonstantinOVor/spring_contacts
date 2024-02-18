package org.example.model.info;

public enum NegativeResponse {
    NEGATIVE_RESPONSE_NAME ("Фамилия Имя Отчество введены некорректно! "),
    NEGATIVE_RESPONSE_EMAIL ("Email введен некорректно! "),
    NEGATIVE_RESPONSE_CHOICE ("Неверный выбор. Попробуйте снова."),
    NEGATIVE_RESPONSE_PHONE_NUMBER ("Номер телефона введен некорректно! "),
    NEGATIVE_RESPONSE_FILE("Файл не найден"),
    NEGATIVE_RESPONSE_FILE_READ("Ошибка при чтении контактов из файла: {}"),
    NEGATIVE_RESPONSE_CONTACT ("Контакт с таким Email не существует! "),
    NEGATIVE_RESPONSE_USER_INPUT("Ошибка чтения пользовательского ввода");
    private final String description;

    NegativeResponse(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
