import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FireStarter {
    public static void main(String[] args) throws IOException {
        for (; ; ) {
            System.out.println("\nВведите рандомное ФИО через пробелы");
            String inputName = (new BufferedReader(new InputStreamReader(System.in))).readLine();

            String[] fullName = inputName.trim().split("\\s+");

            if (inputName.matches("\\d+")) {
                System.out.println("Не используйте цыфры");
            } else if (fullName.length != 3) {
                System.out.println("Некорректный ввод");
            } else {
                System.out.println("Фамилия: " + fullName[0].substring(0, 1).toUpperCase() + fullName[0].substring(1)
                        + "\nИмя: " + fullName[1].substring(0, 1).toUpperCase() + fullName[1].substring(1)
                        + "\nОтчество: " + fullName[2].substring(0, 1).toUpperCase() + fullName[2].substring(1));
            }
        }
    }
}
