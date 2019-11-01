package Bank.Client;

import Bank.Account.BankAccount;

import java.util.ArrayList;

public abstract class BankClient {
    protected ArrayList<BankAccount> accountList;

    public BankClient() {
        this.accountList = new ArrayList<>();
    }

    public ArrayList<BankAccount> getAccountList() {
        return accountList;
    }

    public void removeAccount(int accountIndex) {
        this.accountList.remove(accountIndex);
    }
}
