package task4;

/**
 * Обработчик аннотации @Validate.
 */
public class ValidationHandler {

    /**
     * Выводит имена всех классов, указанных в @Validate над классом.
     */
    public static void printValidatedClasses(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Validate.class)) {
            Validate ann = clazz.getAnnotation(Validate.class);
            Class<?>[] classes = ann.value();

            System.out.print("Классы для валидации в " + clazz.getSimpleName() + ": ");
            if (classes.length == 0) {
                System.out.println("(нет)");
            } else {
                for (int i = 0; i < classes.length; i++) {
                    if (i > 0) System.out.print(", ");
                    System.out.print(classes[i].getName());
                }
                System.out.println();
            }
        } else {
            System.out.println("Класс " + clazz.getSimpleName() + " не аннотирован @Validate.");
        }
    }
}