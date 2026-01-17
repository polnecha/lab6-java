package task1;

import java.lang.reflect.Method;

/**
 * Обработчик, который использует Reflection API для поиска и вызова методов,
 * помеченных аннотацией @Invoke.
 */
public class InvokeHandler {

    /**
     * Вызывает все методы объекта, помеченные аннотацией @Invoke.
     */
    public static void invokeAnnotatedMethods(Object obj) throws Exception {
        if (obj == null) {
            throw new IllegalArgumentException("Переданный объект не может быть null.");
        }

        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Invoke.class)) {
                method.setAccessible(true);
                method.invoke(obj);
            }
        }
    }
}