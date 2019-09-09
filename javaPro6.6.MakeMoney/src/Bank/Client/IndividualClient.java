package Bank.Client;

import Bank.Account.IndividualAccount;

public class IndividualClient extends BankClient{
    public IndividualClient() {
        System.out.println("Cоздан аккаунт для индивидуального предпринимателя");
    }

    public void addAccount(IndividualAccount account) {
        this.accountList.add(account);
    }
}
