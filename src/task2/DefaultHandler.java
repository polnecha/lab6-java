package task2;

/**
 * Обработчик аннотации @Default.
 * Выводит имя класса, указанного в аннотации.
 */
public class DefaultHandler {

    /**
     * Выводит имя класса по умолчанию, указанного в аннотации @Default над классом.
     *
     * @param clazz класс, который может быть аннотирован @Default
     */
    public static void printDefaultClass(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Default.class)) {
            Default annotation = clazz.getAnnotation(Default.class);
            Class<?> defaultClass = annotation.value();
            System.out.println("Класс по умолчанию для " + clazz.getSimpleName() + ": " + defaultClass.getName());
        } else {
            System.out.println("Класс " + clazz.getSimpleName() + " не аннотирован @Default.");
        }
    }
}
