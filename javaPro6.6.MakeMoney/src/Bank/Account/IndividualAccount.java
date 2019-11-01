package Bank.Account;

public class IndividualAccount extends BankAccount{
    private static final double DEPOSIT_PERCENT_BEFORE_1000 = 0.01;
    private static final double DEPOSIT_PERCENT_AFTER_1000 = 0.005;
    private double commissionDeposit;
    private double deposit;

    public IndividualAccount() {
        System.out.println("Открыт счет индивидуального предпринимателя," +
                " комиссия при внесении до 1000 монет - " + DEPOSIT_PERCENT_BEFORE_1000 + "%\n" +
                "комиссия при внесении более 1000 монет включительно - " + DEPOSIT_PERCENT_AFTER_1000 + "%");
    }

    public void addCash(Double coin) {
        if (coin <= 0) {
            System.out.println("Внесите положительное число монет");
        } else if (coin < 1000){
            commissionDeposit = coin * DEPOSIT_PERCENT_BEFORE_1000;
            deposit = coin - commissionDeposit;
            System.out.println("Вы внесли " + coin + " монеты");
            System.out.println(deposit + " монеты поступили на счет: " + this);
            this.cash += coin;
        } else if (coin >= 1000){
            commissionDeposit = coin * DEPOSIT_PERCENT_AFTER_1000;
            deposit = coin - commissionDeposit;
            System.out.println("Вы внесли " + coin + " монеты");
            System.out.println(deposit + " монеты поступили на счет: " + this);
            this.cash += coin;
        }
    }
}
