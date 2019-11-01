package Bank.Account;

public abstract class BankAccount {
    protected Double cash = 0.0;

    public void addCash(Double coin) {
        if (coin <= 0) {
            System.out.println("Внесите положительное число монет");
        } else {
            System.out.println("Вы внесли " + coin + " монеты");
            System.out.println(coin + " монеты поступили на счет: " + this);
            this.cash += coin;
        }
    }

    public void withdrawCash(Double coin) {
        if (coin <= 0) {
            System.out.println("Вы можете снять только положительное число монет " + this.cash);
        } else if (this.cash >= coin) {
            this.cash -= coin;
            System.out.println("Вы сняли " + coin + " монет");
            if (this.cash == 0) {
                System.out.println("Ваш баланс: " + this.cash + " монет");
            }
        } else if (this.cash < coin) {
            System.out.println("Не достаточно монет на счете. Вы не можете снять больше");
        }
    }

    public Double getCash() {
        return this.cash;
    }
}
