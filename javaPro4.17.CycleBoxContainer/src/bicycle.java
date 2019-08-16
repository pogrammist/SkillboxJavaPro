import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bicycle {
    public static void main(String[] args) throws IOException {
        for (; ; ) {
            System.out.println("\nВведите количество контейнеров для отправки");

            int fullBoxs = Integer.parseInt((new BufferedReader(new InputStreamReader(System.in))).readLine());
            int fullContainers = (int) Math.ceil(fullBoxs / 27.0);
            int fullTrucks = (int) Math.ceil(fullContainers / 12.0);
            int countBoxs = 1;

            for (int truck = 1; truck <= fullTrucks; truck++) {
                System.out.println("\nГрузовик " + truck + ":");
                for (int container = 1; container <= 12 && fullContainers >= 1; container++, fullContainers--) {
                    System.out.println("\nКонтейнер " + container + ":");
                    for (int box = 1; box <= 27 && countBoxs <= fullBoxs; box++, countBoxs++) {
                        System.out.println("\nЯщик " + countBoxs);
                    }
                }
            }
        }
    }
}

