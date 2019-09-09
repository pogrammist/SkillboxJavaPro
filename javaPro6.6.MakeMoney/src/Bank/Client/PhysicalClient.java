package Bank.Client;

import Bank.Account.BankAccount;
import Bank.Account.DepositAccount;
import Bank.Account.SimpleAccount;

public class PhysicalClient extends BankClient {
    public PhysicalClient() {
        System.out.println("Cоздан аккаунт для физического лица");
    }

    public void addAccount(SimpleAccount account) {
        this.accountList.add(account);
    }

    public void addAccount(DepositAccount account) {
        this.accountList.add(account);
    }
}
