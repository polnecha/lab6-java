package task1;

import java.lang.reflect.Method;

/**
 * <summary>
 * Обработчик, который использует Reflection API для поиска и вызова методов,
 * помеченных аннотацией @Invoke.
 * </summary>
 */
public class InvokeHandler {

    /**
     * <summary>
     * Вызывает все методы объекта, помеченные аннотацией @Invoke.
     * </summary>
     * <param name="obj">Объект, методы которого нужно проверить.</param>
     * <exception cref="IllegalAccessException">Если метод недоступен.</exception>
     * <exception cref="java.lang.reflect.InvocationTargetException">Если при вызове метода возникло исключение.</exception>
     */
    public static void invokeAnnotatedMethods(Object obj) throws Exception {
        if (obj == null) {
            throw new IllegalArgumentException("Переданный объект не может быть null.");
        }

        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Invoke.class)) {
                method.setAccessible(true); // На случай, если метод private
                method.invoke(obj);
            }
        }
    }
}