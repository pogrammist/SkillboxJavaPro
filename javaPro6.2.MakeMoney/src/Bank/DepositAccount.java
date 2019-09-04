package Bank;

import java.time.LocalDate;

public class DepositAccount extends BankAccount {
    private LocalDate createDateDeposit;
    private LocalDate depositTerm;

    public DepositAccount() {
        System.out.println("Открыт депозитный счет");
    }

    public void addCash(Double coin) {
        if (coin <= 0) {
            System.out.println("Внесите положительное число монет");
        } else {
            this.cash += coin;
            if (this.cash > 0) {
                this.createDateDeposit = LocalDate.now();
                this.depositTerm = createDateDeposit.plusMonths(1);
            }
            System.out.println("Вы внесли " + this.cash + " монеты");
            System.out.println(this.cash + " монеты поступили на счет: " + this);
        }
    }

    public void withdrawCash(Double coin, LocalDate withdrawDate) {
        if (coin <= 0) {
            System.out.println("Вы можете снять только положительное число монет" + this.cash);
        } else if (this.cash >= coin && this.depositTerm.isAfter(withdrawDate)) {
            System.out.println("Срок депозита не закончился");
        } else if (this.cash >= coin) {
            this.cash -= coin;
            System.out.println("Вы сняли " + coin + " монет");
            if (this.cash == 0) {
                this.createDateDeposit = null;
                this.depositTerm = null;
                System.out.println("Ваш баланс: " + this.cash + " монет");
            }
        } else if (this.cash < coin) {
            System.out.println("Не достаточно монет на счете. Вы не можете снять больше");
        }
    }

    public LocalDate getDepositTerm() {
        return this.depositTerm;
    }
}
