package Bank.Account;

public class DebitAccount extends BankAccount {
    private static final double WITHDRAW_PERCENT = 0.01;
    private double commissionWithdraw;
    private double withdraw;

    public DebitAccount() {
        System.out.println("Открыт дебетовый счет, комиссия при снятии " + WITHDRAW_PERCENT + "%");
    }

    public void withdrawCash(Double coin) {
        commissionWithdraw = coin * WITHDRAW_PERCENT;
        withdraw = coin + commissionWithdraw;
        if (coin <= 0) {
            System.out.println("Вы можете снять только положительное число монет " + this.cash);
        } else if (this.cash >= withdraw) {
            this.cash -= withdraw;
            System.out.println("Вы сняли " + withdraw + " монет, в том числе комиссия " + WITHDRAW_PERCENT + "%");
            if (this.cash == 0) {
                System.out.println("Ваш баланс: " + this.cash + " монет");
            }
        } else if (this.cash < withdraw) {
            System.out.println("Не достаточно монет на счете. Вы не можете снять больше. Комиссия " + WITHDRAW_PERCENT + "%");
        }
    }
}
