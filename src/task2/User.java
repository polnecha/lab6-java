package task2;

import java.util.Date;

/**
 * Пример класса с аннотацией @Default.
 */
@Default(Date.class)
public class User {
    private String name;

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