import Bank.Account.*;
import Bank.Client.BankClient;
import Bank.Client.IndividualClient;
import Bank.Client.LegalClient;
import Bank.Client.PhysicalClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

public class BankMakeMoney {
    private static ArrayList<BankClient> clientList = new ArrayList<>();
    private static BankClient currentClient;
    private static ArrayList<BankAccount> currentAccountList;
    private static BankAccount currentAccount;
    private static String[] words;
    private static String input;
    private static final String INSTRUCTION = "LIST CLIENT - вывести список клиентов банка\n" +
            "SELECT CLIENT - выбрать клиента банка\n" +
            "LIST ACCOUNT - вывести список счетов клиента\n" +
            "SELECT ACCOUNT - выбрать счет клиента\n" +
            "CREATE CLIENT PHYSICAL - создать аккаунт для физичского лица\n" +
            "CREATE CLIENT INDIVIDUAL - создать аккаунт для индивидуального предпринимателя\n" +
            "CREATE CLIENT LEGAL - создать аккаунт для юридического лица\n" +
            "CREATE ACCOUNT SIMPLE - создать простой счет для физичского лица\n" +
            "CREATE ACCOUNT DEPOSIT - создать депозитный счет для физичского лица\n" +
            "CREATE ACCOUNT DEBIT - создать дебетовый счет для юридического лица\n" +
            "CREATE ACCOUNT INDIVIDUAL - создать индивидуального предпринимателя\n" +
            "ADD [монеты] - добавить монеты на счет\n" +
            "WITHDRAW [монеты] - снять монеты со счета\n" +
            "WITHDRAW [монеты] [дата завершения депозита] - снять монеты с депозитного счета\n" +
            "CLOSE ACCOUNT - закрыть счет";

    public static void main(String[] args) throws IOException {
        clientList.add(new PhysicalClient());

        clientList.add(new IndividualClient());

        clientList.add(new LegalClient());

        System.out.println(INSTRUCTION);
        for (; ; ) {
            System.out.println("Введите команду");
            input = new BufferedReader(new InputStreamReader(System.in)).readLine();
            words = input.split("\\s+");
            if (input.matches("\\s*")) {
                System.out.println("Пустой ввод");
            } else if (words[0].equals("LIST")) {
                parseList(words);
            } else if (words[0].equals("CREATE")) {
                parseCreate(words);
            } else if (words[0].equals("SELECT")) {
                parseSelect(words);
            } else if (words[0].equals("ADD") && words.length <= 2) {
                parseAdd(words);
            } else if (words[0].equals("WITHDRAW") && words.length <= 3) {
                parseWithdraw(words);
            } else if (words[0].equals("CLOSE") && words.length <= 3) {
                parseCloseAccount(words);
            } else {
                System.out.println("Команда не распознана");
            }
        }
    }

    private static void parseList(String[] words) {
        if (words.length == 1) {
            System.out.println("Команда LIST не содержит аргументов");
        } else if (words[1].matches("CLIENT")
                && clientList.isEmpty()
                && words.length == 2) {
            System.out.println("Список клиентов пуст");

        } else if (words[1].matches("CLIENT")
                && words.length == 2) {
            printСlientList();

        } else if (words[1].matches("ACCOUNT")
                && words.length == 2
                && currentClient == null) {
            System.out.println("Необходимо выбрать аккаунт клиента");

        } else if (words[1].matches("ACCOUNT")
                && words.length == 2
                && currentClient != null) {
            currentAccountList = currentClient.getAccountList();
            if (currentAccountList.isEmpty()) {
                System.out.println("Список счетов пуст");
            } else {
                printAccountList();
            }
        } else {
            System.out.println("Команда LIST не распознана");
        }

    }

    private static void printСlientList() {
        int item = 1;
        for (BankClient client : clientList) {
            System.out.print(item++ + " " + client);
            if (client.equals(currentClient)) {
                System.out.println(" *");
            } else {
                System.out.print("\n");
            }
        }
    }

    private static void printAccountList() {
        for (int item = 0; item < currentAccountList.size(); item++) {
            BankAccount account = currentAccountList.get(item);
            String depositTerm = "";
            if (account instanceof DepositAccount
                    && ((DepositAccount) account).getDepositTerm() != null) {
                depositTerm = ", " + ((DepositAccount) account).getDepositTerm() + " дата завершения депозита";
            }
            String select = "";
            if (account == currentAccount) {
                select = " *";
            }
            System.out.println(item + 1 + " " + account + " - " + account.getCash() + " монет" + depositTerm + select);
        }
    }

    private static void parseCreate(String[] words) {
        if (words.length == 1) {
            System.out.println("Команда CREATE не содержит аргументов");

        } else if (words[1].matches("CLIENT")
                && words.length == 2) {
            System.out.println("Команда CREATE CLIENT требует второй аргумент");

        } else if (words[1].matches("CLIENT")
                && words[2].matches("PHYSICAL")
                && words.length == 3) {
            clientList.add(new PhysicalClient());

        } else if (words[1].matches("CLIENT")
                && words[2].matches("INDIVIDUAL")
                && words.length == 3) {
            clientList.add(new IndividualClient());

        } else if (words[1].matches("CLIENT")
                && words[2].matches("LEGAL")
                && words.length == 3) {
            clientList.add(new LegalClient());

        } else if (words[1].matches("ACCOUNT")
                && words.length == 2) {
            System.out.println("Команда CREATE ACCOUNT требует второй аргумент");

        } else if (words[1].matches("ACCOUNT")
                && words[2].matches("SIMPLE")
                && words.length == 3
                && (currentClient instanceof PhysicalClient)) {
            currentAccountList.add(new SimpleAccount());

        } else if (words[1].matches("ACCOUNT")
                && words[2].matches("DEPOSIT")
                && words.length == 3
                && (currentClient instanceof PhysicalClient)) {
            currentAccountList.add(new DepositAccount());

        } else if (words[1].matches("ACCOUNT")
                && words[2].matches("DEBIT")
                && words.length == 3
                && (currentClient instanceof LegalClient)) {
            currentAccountList.add(new DebitAccount());

        } else if (words[1].matches("ACCOUNT")
                && words[2].matches("INDIVIDUAL")
                && words.length == 3
                && (currentClient instanceof IndividualClient)) {
            currentAccountList.add(new IndividualAccount());

        } else {
            System.out.println("Команда CREATE содержит недопустимый аргумент");
        }
    }

    private static void parseSelect(String[] words) {
        if (words.length == 1) {
            System.out.println("Команда SELECT не содержит аргументов");

        } else if (words[1].matches("CLIENT")
                && words.length == 2) {
            System.out.println("Команда SELECT CLIENT требует второй аргумент");

        } else if (words[1].matches("CLIENT")
                && Integer.parseInt(words[2]) > 0
                && Integer.parseInt(words[2]) <= clientList.size()
                && words.length == 3) {
            currentClient = clientList.get(Integer.parseInt(words[2]) - 1);
            currentAccountList = currentClient.getAccountList();
            currentAccount = null;
            System.out.println("Выбран клиент " + currentClient);

        } else if (words[1].matches("ACCOUNT")
                && words.length == 2) {
            System.out.println("Команда SELECT ACCOUNT требует второй аргумент");

        } else if (words[1].matches("ACCOUNT")
                && currentClient == null
                && words.length == 3) {
            System.out.println("Необходимо выбрать аккаунт клиента");

        } else if (words[1].matches("ACCOUNT")
                && !currentClient.getAccountList().isEmpty()
                && Integer.parseInt(words[2]) > 0
                && Integer.parseInt(words[2]) <= currentClient.getAccountList().size()
                && words.length == 3) {
            currentAccount = currentAccountList.get(Integer.parseInt(words[2]) - 1);
            System.out.println("Выбран счет " + currentAccount);
        } else {
            System.out.println("Команда SELECT содержит недопустимый аргумент");
        }
    }

    private static void parseAdd(String[] words) {
        if (words.length == 1) {
            System.out.println("Команда ADD не содержит аргументов");

        } else if (words[1].matches("\\d+")
                && currentAccount == null
                && words.length == 2) {
            System.out.println("Необходимо выбрать счет клиента");

        } else if (words[1].matches("\\d+")
                && currentAccount != null
                && words.length == 2) {
            currentAccount.addCash(Double.parseDouble(words[1]));

        } else {
            System.out.println("Команда ADD содержит недопустимый аргумент");
        }
    }

    private static void parseWithdraw(String[] words) {
        if (words.length == 1) {
            System.out.println("Команда WITHDRAW не содержит аргументов");

        } else if (words[1].matches("\\d+")
                && words.length == 2
                && currentAccount instanceof SimpleAccount) {
            ((SimpleAccount) currentAccount).withdrawCash(Double.parseDouble(words[1]));

        } else if (words[1].matches("\\d+")
                && words.length == 2
                && currentAccount instanceof IndividualAccount) {
            ((IndividualAccount) currentAccount).withdrawCash(Double.parseDouble(words[1]));

        } else if (words[1].matches("\\d+")
                && words.length == 2
                && currentAccount instanceof DebitAccount) {
            ((DebitAccount) currentAccount).withdrawCash(Double.parseDouble(words[1]));

        } else if (words[1].matches("\\d+")
                && words.length == 2
                && currentAccount instanceof DepositAccount) {
            System.out.println("Вы указали дебетовый счет. Команда WITHDRAW не требует третий аргумент - дата завершения депозита");

        } else if (words[1].matches("\\d+")
                && LocalDate.parse(words[2]).isSupported(ChronoField.EPOCH_DAY)
                && words.length == 3
                && currentAccount instanceof DepositAccount) {
            ((DepositAccount) currentAccount).withdrawCash(Double.parseDouble(words[1]), LocalDate.parse(words[2]));

        } else {
            System.out.println("Команда WITHDRAW содержит недопустимый аргумент");
        }
    }

    private static void parseCloseAccount(String[] words) {
        if (words[1].matches("CLIENT")
                && currentClient == null
                && words.length == 2) {
            System.out.println("Необходимо выбрать аккаунт клиента");

        } else if (words[1].matches("CLIENT")
                && currentClient != null
                && !currentAccountList.isEmpty()
                && words.length == 2) {
            System.out.println("Клиент имеет незакрытые счета");

        } else if (words[1].matches("CLIENT")
                && currentClient != null
                && currentAccountList.isEmpty()
                && words.length == 2) {
            System.out.println("Аккаунт " + currentClient + " закрыт");
            clientList.remove(currentClient);
            currentClient = null;
            currentAccountList = null;
            currentAccount = null;

        } else if (words[1].matches("ACCOUNT")
                && currentAccount == null
                && words.length == 2) {
            System.out.println("Необходимо выбрать счет клиента");

        } else if (words[1].matches("ACCOUNT")
                && currentAccount != null
                && currentAccount.getCash() > 0
                && words.length == 2) {
            System.out.println("Счет имеет положительный баланс и не может быть закрыт");

        } else if (words[1].matches("ACCOUNT")
                && currentAccount != null
                && words.length == 2) {
            System.out.println("Счет " + currentAccount + " закрыт");
            currentAccountList.remove(currentAccount);
            currentAccount = null;

        } else {
            System.out.println("Команда CLOSE содержит недопустимый аргументы");
        }
    }
}
