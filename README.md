# Нечаева Полина ИТ-3,4 Лабораторная №6

## Задание 1. Аннотации

### Задача 1. `@task1.Invoke`

#### Текст задачи
Разработайте аннотацию `@task1.Invoke`, со следующими характеристиками:
- Целью может быть только **МЕТОД**
- Доступна во время исполнения программы
- Не имеет свойств

Создайте класс, содержащий несколько методов, и проаннотируйте хотя бы один из них аннотацией `@task1.Invoke`.  
Реализуйте обработчик (через Reflection API), который находит методы, отмеченные аннотацией `@task1.Invoke`, и вызывает их автоматически.
### Алгоритм решения
```java
// Invoke.java 
package task1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для пометки методов, которые должны быть вызваны автоматически.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Invoke {
    // Нет свойств
}

// SampleClass.java
package task1;

/**
 * Тестовый класс с методами, часть из которых помечена аннотацией @Invoke.
 */
public class SampleClass {

    @Invoke
    public void firstMethod() {
        System.out.println("Вызван метод с аннотацией @Invoke!");
    }

    @Invoke
    public void secondMethod() {
        System.out.println("Вызван второй метод с аннотацией @Invoke!");
    }

    public void regularMethod() {
        System.out.println("Обычный метод (без @Invoke).");
    }
}

// InvokeHandler.java
package task1;

import java.lang.reflect.Method;

/**
 * Обработчик, вызывающий все методы объекта, помеченные аннотацией @Invoke.
 */
public class InvokeHandler {

    /**
     * Находит и вызывает методы с аннотацией @Invoke.
     *
     * @param obj объект, методы которого нужно проверить
     * @throws Exception если произошла ошибка при вызове метода
     */
    public static void invokeAnnotatedMethods(Object obj) throws Exception {
        if (obj == null) {
            throw new IllegalArgumentException("Объект не может быть null.");
        }
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Invoke.class)) {
                method.setAccessible(true);
                method.invoke(obj);
            }
        }
    }
}
```
---

### Задача 2. `@Default`

#### Текст задачи
Разработайте аннотацию `@Default`, со следующими характеристиками:
- Целью может быть **ТИП** или **ПОЛЕ**
- Доступна во время исполнения программы
- Имеет обязательное свойство `value` типа `Class`

Проаннотируйте какой-либо класс данной аннотацией, указав тип по умолчанию.  
Напишите обработчик, который выводит имя указанного класса по умолчанию.
### Алгоритм решения
```java
// task2/Default.java
package task2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания класса по умолчанию.
 * Может применяться к типам и полям.
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Default {
    Class<?> value();
}

// task2/User.java
package task2;

import java.util.Date;

/**
 * Пример класса, аннотированного @Default.
 */
@Default(Date.class)
public class User {
    private String name;

    @Default(String.class)
    private Object description;

    public User(String name) {
        this.name = name;
    }
}

// task2/DefaultHandler.java
package task2;

/**
 * Обработчик аннотации @Default.
 */
public class DefaultHandler {
    public static void printDefaultClass(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Default.class)) {
            Default ann = clazz.getAnnotation(Default.class);
            System.out.println("Класс по умолчанию для " + clazz.getSimpleName() + ": " + ann.value().getName());
        } else {
            System.out.println("Класс " + clazz.getSimpleName() + " не аннотирован @Default.");
        }
    }
}
```
---

### Задача 3. `@ToString`

#### Текст задачи
Разработайте аннотацию `@ToString`, со следующими характеристиками:
- Целью может быть **ТИП** или **ПОЛЕ**
- Доступна во время исполнения программы
- Имеет необязательное свойство `value` с двумя вариантами значений: `YES` или `NO`
- Значение свойства по умолчанию: `YES`

Проаннотируйте класс аннотацией `@ToString`, а одно из полей – с `@ToString(Mode.NO)`.  
Создайте метод, который формирует строковое представление объекта, учитывая только те поля, где `@ToString` имеет значение `YES`.
### Алгоритм решения
```java
// task3/Mode.java
package task3;

/**
 * Режимы аннотации @ToString.
 */
public enum Mode {
    YES,
    NO
}

// task3/ToString.java
package task3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для управления включением полей в строковое представление.
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ToString {
    Mode value() default Mode.YES;
}

// task3/Person.java
package task3;

/**
 * Пример класса с аннотацией @ToString.
 */
@ToString
public class Person {
    @ToString(Mode.YES)
    private String name;

    @ToString(Mode.NO)
    private String password;

    @ToString(Mode.YES)
    private int age;

    private String internalId; // без аннотации — не учитывается

    public Person(String name, String password, int age, String internalId) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.internalId = internalId;
    }
}

// task3/ToStringBuilder.java
package task3;

import java.lang.reflect.Field;

/**
 * Формирует строковое представление объекта на основе @ToString.
 */
public class ToStringBuilder {
    public static String build(Object obj) {
        if (obj == null) return "null";
        Class<?> clazz = obj.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(clazz.getSimpleName()).append("[");

        boolean first = true;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ToString.class)) {
                ToString ann = field.getAnnotation(ToString.class);
                if (ann.value() == Mode.YES) {
                    if (!first) sb.append(", ");
                    first = false;
                    field.setAccessible(true);
                    try {
                        sb.append(field.getName()).append("=").append(field.get(obj));
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
```
---

### Задача 4. `@Validate`

#### Текст задачи
Разработайте аннотацию `@Validate`, со следующими характеристиками:
- Целью может быть **ТИП** или **АННОТАЦИЯ**
- Доступна во время исполнения программы
- Имеет обязательное свойство `value`, типа `Class[]`

Проаннотируйте класс аннотацией `@Validate`, передав список типов для проверки.  
Реализуйте обработчик, который выводит, какие классы указаны в аннотации.
### Алгоритм решения
```java
// task4/Validate.java
package task4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания списка классов, подлежащих проверке.
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
    Class<?>[] value();
}

// task4/DataProcessor.java
package task4;

/**
 * Пример класса, аннотированного @Validate.
 */
@Validate({String.class, Integer.class, Double.class})
public class DataProcessor {
}

// task4/ValidationHandler.java
package task4;

/**
 * Обработчик аннотации @Validate.
 */
public class ValidationHandler {
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
```
---

### Задача 5. `@Two`

#### Текст задачи
Разработайте аннотацию `@Two`, со следующими характеристиками:
- Целью может быть **ТИП**
- Доступна во время исполнения программы
- Имеет два обязательных свойства: `first` типа `String` и `second` типа `int`

Проаннотируйте какой-либо класс аннотацией `@Two`, передав строковое и числовое значения.  
Реализуйте обработчик, который считывает и выводит значения этих свойств.
### Алгоритм решения
```java
// task5/Two.java
package task5;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация с двумя обязательными свойствами: строкой и числом.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Two {
    String first();
    int second();
}

// task5/Config.java
package task5;

/**
 * Пример класса, аннотированного @Two.
 */
@Two(first = "production", second = 100)
public class Config {
}

// task5/TwoHandler.java
package task5;

/**
 * Обработчик аннотации @Two.
 */
public class TwoHandler {
    public static void printTwoValues(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Two.class)) {
            Two ann = clazz.getAnnotation(Two.class);
            System.out.println("first = \"" + ann.first() + "\", second = " + ann.second());
        } else {
            System.out.println("Класс " + clazz.getSimpleName() + " не аннотирован @Two.");
        }
    }
}
```
---

### Задача 6. `@Cache`

#### Текст задачи
Разработайте аннотацию `@Cache`, со следующими характеристиками:
- Целью может быть **ТИП**
- Доступна во время исполнения программы
- Имеет необязательное свойство `value`, типа `String[]`
- Значение свойства по умолчанию: пустой массив

Проаннотируйте класс аннотацией `@Cache`, указав несколько кешируемых областей.  
Создайте обработчик, который выводит список всех кешируемых областей или сообщение, что список пуст.
### Алгоритм решения
```java
// task6/Cache.java
package task6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания кешируемых областей.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    String[] value() default {};
}

// task6/DataService.java
package task6;

/**
 * Пример класса с аннотацией @Cache.
 */
@Cache({"users", "products", "categories"})
public class DataService {
}

// task6/CacheHandler.java
package task6;

/**
 * Обработчик аннотации @Cache.
 */
public class CacheHandler {
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
```
---

## Задание 2. Тестирование

### Задача 4. Тест для `@Validate`

#### Текст задачи
Написать тест, используя фреймворк JUnit, который проверяет корректность работы механизма валидации классов, отмеченных аннотацией `@Validate`.
- Создать класс с аннотацией `@Validate`, указывающей массив типов для проверки.
- Использовать тест, который вызывает обработчик и проверяет, что список классов, переданный в аннотации, корректно извлекается и при передаче пустого массива выбрасывается исключение `IllegalArgumentException`.
- В тесте использовать аннотацию `@Test` с параметром `expected` (или `assertThrows` в JUnit 5).
### Алгоритм решения
```java
```
---

### Задача 6. Тест для `@Two`

#### Текст задачи
Разработайте тест, используя фреймворк JUnit, проверяющий корректность обработки аннотации `@Two`, если её свойства заданы некорректно. Например, строковое свойство `first` пустое (`""`), а числовое `second` отрицательное.
- Создайте вспомогательный класс с аннотацией `@Two(first = "", second = -1)`.
- В тесте реализуйте метод, который через Reflection считывает значения аннотации.
- Если одно из свойств нарушает ожидаемые условия (`first` – пустая строка, `second < 0`), то должен быть выброшен `IllegalArgumentException`.
- Используйте `assertThrows()` из JUnit для проверки выбрасываемого исключения.
### Алгоритм решения
```java
```