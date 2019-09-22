import java.util.HashMap;

public class CustomerStorage {
    private HashMap<String, Customer> storage;
    private static final String EMAIL_PATTERN = "[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String NUMBER_PATTERN = "\\+{1}\\d{11}";

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
        if (components.length < 4) {
            throw new IllegalArgumentException("Wrong add command format. Correct it");
        }
        if (!components[2].matches(EMAIL_PATTERN)) {
            throw new IllegalArgumentException("Wrong email format. Correct it");
        }
        if (!components[3].matches(NUMBER_PATTERN)) {
            throw new IllegalArgumentException("Wrong telephone number format. Correct it");
        }
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}