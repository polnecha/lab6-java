package task5;

/**
 * Обработчик аннотации @Two.
 */
public class TwoHandler {

    /**
     * Выводит значения свойств first и second из аннотации @Two над классом.
     */
    public static void printTwoValues(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Two.class)) {
            Two ann = clazz.getAnnotation(Two.class);
            System.out.println("first = \"" + ann.first() + "\", second = " + ann.second());
        } else {
            System.out.println("Класс " + clazz.getSimpleName() + " не аннотирован @Two.");
        }
    }
}