import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RoadCamera {
    public static void main(String[] args) throws IOException {
        //Параметры программы
        int maxOncomingSpeed = 60; // km/h
        int speedFineGrade = 20; // km/h
        int finePerGrade = 1000; // RUB
        int criminalSpeed = 180; // km/h

        //=============================================================
        for (; ; ) {
            System.out.println("Введите скорость автомобиля:");
            //Скорость автомобиля
            String inputText = (new BufferedReader(new InputStreamReader(System.in))).readLine().trim();

            if (inputText.matches("\\d+")) {
                int oncomingSpeed = Integer.parseInt(inputText);
                if (oncomingSpeed >= criminalSpeed) {
                    System.out.println("Вызов полиции...");
                } else if (oncomingSpeed > maxOncomingSpeed) {
                    int overSpeed = oncomingSpeed - maxOncomingSpeed;
                    int gradesCount = overSpeed / speedFineGrade + 1;
                    int fine = finePerGrade * gradesCount;
                    System.out.println("overSpeed: " + overSpeed);
                    System.out.println("gradesCount: " + gradesCount);
                    System.out.println("Сумма штрафа: " + fine);
                } else {
                    System.out.println("Скорость не превышена");
                }
            } else {
                System.out.println("Вы ввели неверные данные");
            }
        }
    }
}