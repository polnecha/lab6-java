package task1;

/**
 * Пример класса, содержащего несколько методов, один из которых помечен аннотацией @Invoke.
 */
public class SampleClass {

    /**
     * Метод, который будет автоматически вызван обработчиком.
     */
    @Invoke
    public void annotatedMethod() {
        System.out.println("Вызван метод с аннотацией @Invoke!");
    }

    /**
     * Обычный метод без аннотации.
     */
    public void regularMethod() {
        System.out.println("Это обычный метод.");
    }

    /**
     * Еще один метод с аннотацией @Invoke.
     */
    @Invoke
    public void anotherAnnotatedMethod() {
        System.out.println("Вызван второй метод с аннотацией @Invoke!");
    }
}