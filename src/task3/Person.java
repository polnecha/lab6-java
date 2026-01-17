package task3;

/**
 * Пример класса с аннотацией @ToString.
 */
@ToString  // можно без скобок — будет Mode.YES по умолчанию
public class Person {
    @ToString(Mode.YES)
    private String name;

    @ToString(Mode.NO)
    private String password;

    @ToString(Mode.YES)
    private int age;

    // Поле без аннотации — не будет включено
    private String internalId;

    public Person(String name, String password, int age, String internalId) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.internalId = internalId;
    }

    // Геттеры для доступа через Reflection
    public String getName() { return name; }
    public String getPassword() { return password; }
    public int getAge() { return age; }
    public String getInternalId() { return internalId; }
}