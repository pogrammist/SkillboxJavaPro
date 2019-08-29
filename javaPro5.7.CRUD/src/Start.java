import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Start {

    private static ArrayList<String> todoList = new ArrayList<>();
    private static String input;
    private static String[] words;
    private static final String INSTRUCTION = "LIST - вывести список команд\n" +
            "ADD [текст] - добавить новое дело\n" +
            "ADD [номер] [текст] - добавить промежуточное дело\n" +
            "EDIT [номер] [текст] - редактировать дело\n" +
            "DELETE [номер] - удалить дело";

    public static void main(String[] args) throws IOException {
        System.out.println(INSTRUCTION);
        for (; ; ) {
            System.out.println("Введите команду");
            String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
            String[] words = input.split("\\s+");
            if (input.matches("\\s*")) {
                System.out.println("Пустой ввод");
            } else if (input.equals("LIST") && todoList.isEmpty()) {
                System.out.println("Список дел пуст");
            } else if (input.equals("LIST")) {
                System.out.println("Список дел:");
                for (int item = 0; item < todoList.size(); item++) {
                    System.out.println(item + 1 + " " + todoList.get(item));
                }
            } else if (words[0].equals("ADD")) {
                parseAdd(input, words);
            } else if (words[0].equals("EDIT") && words.length <= 3) {
                parseEdit(words);
            } else if (words[0].equals("DELETE")) {
                parseDelete(words);
            } else {
                System.out.println("Команда не распознана");
            }
        }
    }

    private static void parseAdd(String input, String[] words) {
        if (words.length == 1) {
            System.out.println("Команда ADD не содержит аргументов");

        } else if (words[1].matches("\\D+")) {
            todoList.add(input.substring(input.indexOf(" ")).trim());
            System.out.println("Добавлена новая задача");

        } else if (words[1].matches("\\d+") && words.length == 2) {
            System.out.println("Команда ADD требует второй аргумент");

        } else if (words[1].matches("\\d+") && Integer.parseInt(words[1]) > 0 && Integer.parseInt(words[1]) <= todoList.size()) {
            todoList.add(Integer.parseInt(words[1]) - 1, words[2]);
            System.out.println("Добавлена промежуточная задача");

        } else {
            System.out.println("Команда ADD содержит недопустимый аргумент");
        }
    }

    private static void parseEdit(String[] words) {
        if (words.length == 1) {
            System.out.println("Команда EDIT не содержит аргументов");

        } else if (words[1].matches("\\d+") && words.length == 2) {
            System.out.println("Команда EDIT требует второй аргумент");

        } else if (words[1].matches("\\D+") || Integer.parseInt(words[1]) <= 0 || Integer.parseInt(words[1]) > todoList.size()) {
            System.out.println("Команда EDIT содержит недопустимый аргумент");

        } else {
            todoList.remove(Integer.parseInt(words[1]) - 1);
            todoList.add(Integer.parseInt(words[1]) - 1, words[2]);
            System.out.println("Задача " + Integer.parseInt(words[1]) + " изменена");
        }
    }

    private static void parseDelete(String[] words) {
        if (words.length == 1) {
            System.out.println("Команда DELETE требует второй аргумент");

        } else if (words[1].matches("\\D+") || Integer.parseInt(words[1]) <= 0 || Integer.parseInt(words[1]) > todoList.size()) {
            System.out.println("Команда DELETE содержит недопустимый аргумент");

        } else {
            todoList.remove(Integer.parseInt(words[1]) - 1);
            System.out.println("Задача " + Integer.parseInt(words[1]) + " удалена");
        }
    }
}