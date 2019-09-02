package Bank;

public class BankAccount {
    private Double cash = 0.0;

    public void addCash(Double cash) {
        if (cash <= 0) {
            System.out.println("Внесите положительное число монет");
        } else {
            this.cash += cash;
            System.out.println("Вы внесли " + this.cash + " монеты");
            System.out.println(this.cash + " монеты поступили на счет: " + this);
        }
    }

    public void withdrawCash(Double cash) {
        if (cash <= 0) {
            System.out.println("Вы можете снять только положительное число монет" + this.cash);
        } else if (this.cash >= cash) {
            this.cash -= cash;
            System.out.println("Вы сняли " + cash + " монет");
            if (this.cash == 0) {
                System.out.println("Ваш баланс: " + this.cash + " монет");
            }
        } else if (this.cash < cash) {
            System.out.println("Не достаточно монет на счете. Вы не можете снять больше");
        }
    }

    public Double getCash() {
        return this.cash;
    }
}
