import java.util.Scanner;

/**
 * Утилитный класс для безопасного чтения данных от пользователя.
 */
public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Читает целое число из консоли с обработкой некорректного ввода.
     *
     * @param prompt Приглашение для ввода (например, "Ваш выбор: ")
     * @return Введённое целое число.
     */
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Ошибка: введите целое число.");
                scanner.next(); // очистить некорректный ввод
            }
        }
    }
}