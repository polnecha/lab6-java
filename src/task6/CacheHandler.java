package task6;

/**
 * Обработчик аннотации @Cache.
 */
public class CacheHandler {

    /**
     * Выводит список кешируемых областей из аннотации @Cache над классом.
     */
    public static void printCacheRegions(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Cache.class)) {
            Cache ann = clazz.getAnnotation(Cache.class);
            String[] regions = ann.value();

            if (regions.length == 0) {
                System.out.println("Кешируемые области для " + clazz.getSimpleName() + ": список пуст");
            } else {
                System.out.print("Кешируемые области для " + clazz.getSimpleName() + ": ");
                for (int i = 0; i < regions.length; i++) {
                    if (i > 0) System.out.print(", ");
                    System.out.print(regions[i]);
                }
                System.out.println();
            }
        } else {
            System.out.println("Класс " + clazz.getSimpleName() + " не аннотирован @Cache.");
        }
    }
}