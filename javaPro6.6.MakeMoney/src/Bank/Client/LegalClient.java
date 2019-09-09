package Bank.Client;

import Bank.Account.DebitAccount;

public class LegalClient extends BankClient {
    public LegalClient() {
        System.out.println("Cоздан аккаунт для юридического лица");
    }

    public void addAccount(DebitAccount account) {
        this.accountList.add(account);
    }
}