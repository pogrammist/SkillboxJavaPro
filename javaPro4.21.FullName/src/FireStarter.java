import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FireStarter {
    public static void main(String[] args) throws IOException {
        for (; ; ) {
            System.out.println("\nВведите рандомное ФИО через пробелы");
            String inputName = (new BufferedReader(new InputStreamReader(System.in))).readLine();
            int countWords = inputName.split("\\s+").length;
            if (inputName.matches("\\s*")) {
                continue;
            } else if (!inputName.matches("\\D+")) {
                System.out.println("Исключите цифры");
                continue;
            } else if (countWords < 3 || countWords > 3) {
                System.out.println("Некорректный ввод");
                continue;
            } else {
                String surname = inputName.substring(0, inputName.indexOf(" "));
                String surnameUp = "Фамилия: " + Character.toUpperCase(surname.charAt(0)) + surname.substring(1);

                String name = inputName.substring(inputName.indexOf(" "), inputName.lastIndexOf(" "));
                String nameWithSpace = name.substring(1);
                String nameUp = "Имя: " + Character.toUpperCase(nameWithSpace.charAt(0)) + nameWithSpace.substring(1);

                String patronymic = inputName.substring(inputName.lastIndexOf(" "));
                String patronymicWithSpace = patronymic.substring(1);
                String patronymicUp = "Отчество: " + Character.toUpperCase(patronymicWithSpace.charAt(0)) + patronymicWithSpace.substring(1);

                System.out.println(surnameUp + "\n" + nameUp + "\n" + patronymicUp);
            }
        }
    }
}
