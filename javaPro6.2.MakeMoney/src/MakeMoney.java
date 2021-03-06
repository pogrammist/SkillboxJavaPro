import Bank.BankAccount;
import Bank.DebitAccount;
import Bank.DepositAccount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

public class MakeMoney {
    private static ArrayList<BankAccount> accountList = new ArrayList<>();
    private static BankAccount account = new BankAccount();
    private static String[] words;
    private static String input;
    //    private static final Character VOLUTE[] = new Character[]{'₽', '$', '€'};
    private static final String INSTRUCTION = "LIST - вывести список счетов\n" +
            "CREATE DEBIT - создать новый дебетовый счет\n" +
            "CREATE DEPOSIT - создать новый депозитный счет\n" +
            "ADD [номер счета] [монеты] - добавить монеты на счет\n" +
            "WITHDRAW [номер дебетового счета] [монеты] - снять монеты с дебетового счета\n" +
            "WITHDRAW [номер депозитного счета] [монеты] [дата завершения депозита] - снять монеты с депозитного счета\n" +
            "CLOSE ACCOUNT [номер счета] - закрыть счет";

    public static void main(String[] args) throws IOException {
        System.out.println(INSTRUCTION);
        for (; ; ) {
            System.out.println("Введите команду");
            input = new BufferedReader(new InputStreamReader(System.in)).readLine();
            words = input.split("\\s+");
            if (input.matches("\\s*")) {
                System.out.println("Пустой ввод");
            } else if (input.equals("LIST") && accountList.isEmpty()) {
                System.out.println("Список счетов пуст");
            } else if (input.equals("LIST")) {
                System.out.println("Список счетов:");
                getAccountList();
            } else if (words[0].equals("CREATE")) {
                parseCreate(words);
            } else if (words[0].equals("ADD") && words.length <= 4) {
                parseAdd(words);
            } else if (words[0].equals("WITHDRAW") && words.length <= 4) {
                parseWithdraw(words);
            } else if (words[0].equals("CLOSE") && words[1].equals("ACCOUNT")) {
                parseCloseAccount(words);
            } else {
                System.out.println("Команда не распознана");
            }
        }
    }

    private static void getAccountList() {
        for (int item = 0; item < accountList.size(); item++) {
            account = accountList.get(item);
            System.out.print(item + 1 + " " + account + " - " + account.getCash() + " монет");
            if (account.getClass().equals(DepositAccount.class)
                    && ((DepositAccount) account).getDepositTerm() != null) {
                System.out.println(", " + ((DepositAccount) account).getDepositTerm() + " дата завершения депозита");
            } else {
                System.out.print("\n");
            }
        }
    }

    private static void parseCreate(String[] words) {
        if (words.length == 1) {
            System.out.println("Команда CREATE не содержит аргументов");

        } else if (words[1].matches("DEBIT")
                && words.length == 2) {
            accountList.add(new DebitAccount());

        } else if (words[1].matches("DEPOSIT")
                && words.length == 2) {
            accountList.add(new DepositAccount());

        } else {
            System.out.println("Команда CREATE содержит недопустимый аргумент");
        }
    }

    private static void parseAdd(String[] words) {
        if (words.length == 1) {
            System.out.println("Команда ADD не содержит аргументов");

        } else if (words[1].matches("\\d+")
                && words.length == 2) {
            System.out.println("Команда ADD требует второй аргумент");

        } else if (words[1].matches("\\d+")
                && Integer.parseInt(words[1]) > 0
                && Integer.parseInt(words[1]) <= accountList.size()
                && words.length == 3
        ) {
            account = accountList.get(Integer.parseInt(words[1]) - 1);
            account.addCash(Double.parseDouble(words[2]));

        } else {
            System.out.println("Команда ADD содержит недопустимый аргумент");
        }
    }

    private static void parseWithdraw(String[] words) {
        if (words.length == 1) {
            System.out.println("Команда WITHDRAW не содержит аргументов");

        } else if (words[1].matches("\\d+")
                && words.length == 2) {
            System.out.println("Команда WITHDRAW требует второй аргумент");

        } else if (words[1].matches("\\d+")
                && Integer.parseInt(words[1]) > 0
                && Integer.parseInt(words[1]) <= accountList.size()
                && words[2].matches("\\d+")) {
            if (words.length == 3) {
                if ((accountList.get(Integer.parseInt(words[1]) - 1)).getClass().equals(DebitAccount.class)) {
                    account = accountList.get(Integer.parseInt(words[1]) - 1);
                    ((DebitAccount) account).withdrawCash(Double.parseDouble(words[2]));
                } else {
                    System.out.println("Вы указали депозитный счет. Команда WITHDRAW требует третий аргумент");
                }
            } else if (words.length == 4
                    && LocalDate.parse(words[3]).isSupported(ChronoField.EPOCH_DAY)) {
                if ((accountList.get(Integer.parseInt(words[1]) - 1)).getClass().equals(DepositAccount.class)) {
                    account = accountList.get(Integer.parseInt(words[1]) - 1);
                    ((DepositAccount) account).withdrawCash(Double.parseDouble(words[2]), LocalDate.parse(words[3]));
                } else {
                    System.out.println("Вы указали дебетовый счет. Команда WITHDRAW не требует третий аргумент");
                }
            }
        } else {
            System.out.println("Команда WITHDRAW содержит недопустимый аргумент");
        }
    }

    private static void parseCloseAccount(String[] words) {
        if (words.length == 2) {
            System.out.println("Команда CLOSE ACCOUNT не содержит аргументов");

        } else if (words[2].matches("\\d+")
                && Integer.parseInt(words[2]) > 0
                && Integer.parseInt(words[2]) <= accountList.size()
                && words.length == 3) {
            account = accountList.get(Integer.parseInt(words[2]) - 1);
            if (account.getCash() > 0) {
                System.out.println("Счет имеет положительный баланс и не может быть закрыт");
            } else {
                System.out.println("Счет " + accountList.get(Integer.parseInt(words[2]) - 1) + " закрыт");
                accountList.remove(Integer.parseInt(words[2]) - 1);
            }
        } else {
            System.out.println("Команда CLOSE ACCOUNT содержит недопустимый аргумент");
        }
    }
}
