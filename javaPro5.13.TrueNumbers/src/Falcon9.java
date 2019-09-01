import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

public class Falcon9 {

    private static final String CHARS[] = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
    private static ArrayList<String> carNumbers = new ArrayList<>();
    private static TreeSet<String> carNumbersTreeSet = new TreeSet<>();
    private static HashSet<String> carNumbersHashSet = new HashSet<>();

    private Falcon9() {
        for (int a = 0; a < CHARS.length; a++) {
            for (int num = 1; num < 1000; num++) {
                for (int reg = 1; reg <= 95; reg++) {
                    carNumbers.add(CHARS[a] + String.format("%03d", num) + CHARS[a] + CHARS[a] + String.format("%02d", reg) + "RUS");
                }
            }
        }
        Collections.shuffle(carNumbers);
        carNumbersHashSet = new HashSet<>(carNumbers);
        carNumbersTreeSet = new TreeSet(carNumbers);
    }

    public static void main(String[] args) throws IOException {
        Falcon9 checker = new Falcon9();
        System.out.println("Введите регистрационный номер автомобиля в формате \"а123вс45rus\":");
        for (; ; ) {
            BufferedReader inputNumber = new BufferedReader(new InputStreamReader(System.in));
            String currentNumber = inputNumber.readLine().trim().toUpperCase();
            getTrueNumberBruteForce(currentNumber);
            getTrueNumberBinarySearch(currentNumber);
            getTrueNumberHashSet(currentNumber);
            getTrueNumberTreeSet(currentNumber);
        }
    }

    private static void getTrueNumberBruteForce(String currentNumber) {
        boolean find = false;
        long startTime = System.nanoTime();
        for (String item : carNumbers) {
            if (currentNumber.equals(item)) {
                System.out.printf("true (%tL ms)\n", System.nanoTime() - startTime);
                find = true;
                break;
            }
        }
        if (find != true) {
            System.out.printf("false (%tL ms)\n", System.nanoTime() - startTime);
        }
    }

    private static void getTrueNumberBinarySearch(String currentNumber) {
        Collections.sort(carNumbers);
        long startTime = System.nanoTime();
        int find = Collections.binarySearch(carNumbers, currentNumber);
        if (find > 0) {
            System.out.printf("true (%tL ms)\n", System.nanoTime() - startTime);
        } else {
            System.out.printf("false (%tL ms)\n", System.nanoTime() - startTime);
        }
    }

    private static void getTrueNumberHashSet(String currentNumber) {
        long startTime = System.nanoTime();
        if (carNumbersHashSet.contains(currentNumber)) {
            System.out.printf("true (%tL ms)\n", System.nanoTime() - startTime);
        } else {
            System.out.printf("false (%tL ms)\n", System.nanoTime() - startTime);
        }
    }

    private static void getTrueNumberTreeSet(String currentNumber) {
        long startTime = System.nanoTime();
        if (carNumbersTreeSet.contains(currentNumber)) {
            System.out.printf("true (%tL ms)\n", System.nanoTime() - startTime);
        } else {
            System.out.printf("false (%tL ms)\n", System.nanoTime() - startTime);
        }
    }
}