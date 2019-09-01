import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
    private static TreeMap<String, String> phoneBook = new TreeMap<String, String>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final String TRUE_NUMBER = "\\d{11}\\d?";
    private static final String TRUE_NAME = "\\D+\\d?";
    private static final int MAX_NUMBER_LENGTH = 18;
    private static final String INSTRUCTION = "LIST - list subscribers";

    public static void main(String[] args) throws IOException {
        System.out.println(INSTRUCTION);
        for (; ; ) {
            System.out.println("Please, type telephone number or name:");
            String input = reader.readLine().trim();
            if (input.matches("\\s*")) {
                System.out.println("Empty input");
            } else if (input.equals("LIST") && phoneBook.isEmpty()) {
                System.out.println("Email list is empty");
            } else if (input.equals("LIST")) {
                for (Map.Entry item : phoneBook.entrySet()) {
                    System.out.println(item.getKey() + " -- +" + item.getValue());
                }
            } else if (phoneBook.get(getTrueName(input)) != null) {
                //Found subscriber of name
                System.out.println(getTrueName(input) + " -- +" + phoneBook.get(getTrueName(input)));
            } else if (input.matches(TRUE_NAME)) {
                //New subscriber
                System.out.println("You have entered a new subscriber name. Please, type phone number:");
                String number = getTrueNumber(reader.readLine());
                if (number.matches(TRUE_NUMBER)) {
                    phoneBook.put(getTrueName(input), number);
                    System.out.println("Subscriber and number saved");
                } else {
                    System.out.println("You entered an incorrect number");
                }
            } else if (getTrueNumber(input).matches(TRUE_NUMBER) && input.length() <= MAX_NUMBER_LENGTH) {
                boolean find = false;
                for (String name : phoneBook.keySet()) {
                    //Found subscriber of number
                    if (getTrueNumber(input).equals(phoneBook.get(name))) {
                        find = true;
                        System.out.println(name + " -- +" + phoneBook.get(name));
                    }
                }
                if (!find) {
                    //New number
                    System.out.println("You have entered a new phone number. Please, type subscriber name:");
                    String name = getTrueName(reader.readLine().trim());
                    if (phoneBook.get(name) == null) {
                        phoneBook.put(name, getTrueNumber(input));
                        System.out.println("Subscriber and number saved");
                    } else {
                        System.out.println("You entered an incorrect name");
                    }
                }
            } else {
                System.out.println("Input not recognized");
            }
        }
    }

    public static String getTrueNumber(String input) {
        String number = input.replaceAll("[^0-9]", "");
        return number;
    }

    public static String getTrueName(String input) {
        String name = Character.toUpperCase(input.charAt(0)) + input.substring(1);
        return name;
    }
}

