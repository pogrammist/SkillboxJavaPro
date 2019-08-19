import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FireStarter {
    public static void main(String[] args) throws IOException {
        for (; ; ) {
            System.out.println("\nВведите рандомное ФИО через пробелы");
            String inputName = (new BufferedReader(new InputStreamReader(System.in))).readLine().trim();

            if (inputName.equals("")) {
                continue;
            } else if (searchDigit(inputName)) {
                System.out.println("Не используйте числа");
            } else if (searchWord(inputName)) {
                System.out.println("Вы ввесли всего одно слово");
            } else if (searchOfOneSpace(inputName)) {
                System.out.println("Вы ввесли всего два слова");
            } else if (searchOfTwoSpaces(inputName)) {

                String surname = inputName.substring(0, inputName.indexOf(" "));
                String surnameUp = "Фамилия: " + Character.toUpperCase(surname.charAt(0)) + surname.substring(1);

                String name = inputName.substring(inputName.indexOf(" "), inputName.lastIndexOf(" "));
                String nameWithSpace = name.substring(1);
                String nameUp = "Имя: " + Character.toUpperCase(nameWithSpace.charAt(0)) + nameWithSpace.substring(1);

                String patronymic = inputName.substring(inputName.lastIndexOf(" "));
                String patronymicWithSpace = patronymic.substring(1);
                String patronymicUp = "Отчество: " + Character.toUpperCase(patronymicWithSpace.charAt(0)) + patronymicWithSpace.substring(1);

                System.out.println(surnameUp + "\n" + nameUp + "\n" + patronymicUp);
            } else {
                System.out.println("Некорректный ввод");
            }
        }
    }

    private static boolean searchDigit(String inputName) {
        String digit = "0123456789";
        int charsOfWord = inputName.length();
        int charsOfDigit = digit.length();
        boolean findDigit = false;
        for (int startChar = 0; startChar < charsOfWord; startChar++) {
            String oneChar = inputName.substring(startChar, startChar + 1);
            for (int startDigit = 0; startDigit < charsOfDigit; startDigit++) {
                String oneDigit = digit.substring(startDigit, startDigit + 1);
                if (oneChar.equals(oneDigit)) {
                    findDigit = true;
                    break;
                } else {
                    findDigit = false;
                }
            }
        }
        return findDigit;
    }

    private static boolean searchWord(String inputName) {
        int charsOfWord = inputName.length();
        boolean findWord = true;
        for (int startChar = 0; startChar < charsOfWord; startChar++) {
            String oneChar = inputName.substring(startChar, startChar + 1);
            if (oneChar.equals(" ")) {
                findWord = false;
                break;
            } else {
                findWord = true;
            }
        }
        return findWord;
    }

    private static boolean searchOfOneSpace(String inputName) {
        String firstWord = inputName.substring(0, inputName.indexOf(" "));
        String secondWord = inputName.substring(0, inputName.lastIndexOf(" "));
        return firstWord.equals(secondWord);
    }

    private static boolean searchOfTwoSpaces(String inputName) {
        String firstPart = inputName.substring(0, inputName.lastIndexOf(" "));
        boolean firstSpace = (firstPart.substring(firstPart.indexOf(" "), firstPart.lastIndexOf(" ") + 1)).equals(" ");
        boolean secondSpace = (inputName.substring(inputName.lastIndexOf(" "), inputName.lastIndexOf(" ") + 1)).equals(" ");
        if (firstSpace && secondSpace) {
            return true;
        } else {
            return false;
        }
    }
}
