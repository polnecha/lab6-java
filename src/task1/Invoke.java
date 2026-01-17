package task1;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <summary>
 * Аннотация для пометки методов, которые должны быть автоматически вызваны
 * обработчиком во время выполнения программы.
 * </summary>
 * <remarks>
 * Может применяться только к методам и доступна во время выполнения.
 * </remarks>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Invoke {
    // Нет свойств
}