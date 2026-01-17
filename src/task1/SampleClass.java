package task1;

/**
 * <summary>
 * Пример класса, содержащего несколько методов, один из которых помечен аннотацией @task1.Invoke.
 * </summary>
 */
public class SampleClass {

    /**
     * <summary>
     * Метод, который будет автоматически вызван обработчиком.
     * </summary>
     */
    @Invoke
    public void annotatedMethod() {
        System.out.println("Вызван метод с аннотацией @Invoke!");
    }

    /**
     * <summary>
     * Обычный метод без аннотации.
     * </summary>
     */
    public void regularMethod() {
        System.out.println("Это обычный метод.");
    }

    /**
     * <summary>
     * Еще один метод с аннотацией @task1.Invoke.
     * </summary>
     */
    @Invoke
    public void anotherAnnotatedMethod() {
        System.out.println("Вызван второй метод с аннотацией @Invoke!");
    }
}