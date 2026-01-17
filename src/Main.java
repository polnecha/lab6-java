/**
 * Главный класс с интерактивным меню для демонстрации всех задач.
 */
public class Main {

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nЛАБОРАТОРНАЯ РАБОТА №6");
            System.out.println("1. Задача 1: Аннотация @Invoke");
            System.out.println("2. Задача 2: Аннотация @Default");
            System.out.println("3. Задача 3: Аннотация @ToString");
            System.out.println("4. Задача 4: Аннотация @Validate");
            System.out.println("5. Задача 5: Аннотация @Two");
            System.out.println("6. Задача 6: Аннотация @Cache");
            System.out.println("0. Выход");

            int choice = InputUtils.readInt("Ваш выбор: ");

            switch (choice) {
                case 1 -> runTask1();
                case 2 -> runTask2();
                case 3 -> runTask3();
                case 4 -> runTask4();
                case 5 -> runTask5();
                case 6 -> runTask6();
                case 0 -> {
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Нет такого пункта!");
            }
        }
    }

    private static void runTask1() {
        System.out.println("\nЗадача 1: @Invoke");
        try {
            task1.SampleClass obj = new task1.SampleClass();
            task1.InvokeHandler.invokeAnnotatedMethods(obj);
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void runTask2() {
        System.out.println("\nЗадача 2: @Default");
        task2.DefaultHandler.printDefaultClass(task2.User.class);
    }

    private static void runTask3() {
        System.out.println("\nЗадача 3: @ToString");
        task3.Person person = new task3.Person("Alice", "secret123", 30, "id999");
        String result = task3.ToStringBuilder.build(person);
        System.out.println(result);
    }

    private static void runTask4() {
        System.out.println("\nЗадача 4: @Validate");
        task4.ValidationHandler.printValidatedClasses(task4.DataProcessor.class);
    }

    private static void runTask5() {
        System.out.println("\nЗадача 5: @Two");
        task5.TwoHandler.printTwoValues(task5.Config.class);
    }

    private static void runTask6() {
        System.out.println("\nЗадача 6: @Cache");
        task6.CacheHandler.printCacheRegions(task6.DataService.class);
    }
}