package task3;

import java.util.Date;

/**
 * Пример класса с аннотацией @ToString.
 */
@ToString
public class Person {
    @ToString(Mode.YES)
    private String name;

    @ToString(Mode.NO)
    private String password;

    @ToString(Mode.YES)
    private int age;

    private String internalId;

    public Person(String name, String password, int age, String internalId) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.internalId = internalId;
    }
}