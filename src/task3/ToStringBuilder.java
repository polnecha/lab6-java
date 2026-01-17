package task3;

import java.lang.reflect.Field;

/**
 * Утилита для формирования строкового представления объекта на основе @ToString.
 */
public class ToStringBuilder {

    /**
     * Формирует строку вида "ClassName[field1=value1, field2=value2, ...]",
     * включая только поля с @ToString(value = Mode.YES).
     */
    public static String build(Object obj) {
        if (obj == null) {
            return "null";
        }

        Class<?> clazz = obj.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(clazz.getSimpleName()).append("[");

        boolean first = true;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ToString.class)) {
                ToString ann = field.getAnnotation(ToString.class);
                if (ann.value() == Mode.YES) {
                    if (!first) {
                        sb.append(", ");
                    }
                    first = false;
                    field.setAccessible(true);
                    try {
                        Object value = field.get(obj);
                        sb.append(field.getName()).append("=").append(value);
                    } catch (IllegalAccessException e) {
                        sb.append(field.getName()).append("=<error>");
                    }
                }
            }
        }

        sb.append("]");
        return sb.toString();
    }
}