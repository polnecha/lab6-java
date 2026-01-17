package task2;

import java.util.Date;

/**
 * Пример класса, аннотированного @Default с указанием типа по умолчанию.
 */
@Default(value = Date.class)  // или просто @Default(Date.class)
public class User {
    private String name;

    // Поле тоже можно аннотировать (пример)
    @Default(String.class)
    private Object description;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "'}";
    }
}