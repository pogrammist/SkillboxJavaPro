import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Start {

    private static HashSet<String> emails = new HashSet<>();
    private static String input;
    private static String[] words;
    private static final String EMAIL_PATTERN = "[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static void main(String[] args) throws IOException {
        for (; ; ) {
            System.out.println("Введите команду");
            String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
            String[] words = input.split("\\s+");
            if (input.matches("\\s*")) {
                System.out.println("Пустой ввод");
            } else if (input.equals("LIST") && emails.isEmpty()) {
                System.out.println("Список email пуст");
            } else if (input.equals("LIST")) {
                System.out.println("Список email:");
                for (String item : emails) {
                    System.out.println(item);
                }
            } else if (words[0].equals("ADD")) {
                parseAdd(input, words);
            } else {
                System.out.println("Email некорректен");
            }
        }
    }

    private static void parseAdd(String input, String[] words) {
        if (words.length == 1) {
            System.out.println("Команда ADD не содержит аргументов");
        } else if (words.length == 2 && words[1].matches(EMAIL_PATTERN)) {
            emails.add(words[1]);
            System.out.println("Email сохранен");
        } else if (!words[1].matches(EMAIL_PATTERN)) {
            System.out.println("Email некорректен");
        } else {
            System.out.println("Команда ADD некорректна");
        }
    }
}