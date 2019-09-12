package Tesla;

public class SalesManager extends EmployeeComparable implements Employee{
    private double moneyUp;
    private double fixSalary;

    SalesManager() {
        moneyUp = Math.random() * 20000000;
    }

    double getMoneyUp() {
        return moneyUp;
    }

    public void setFixSalary(double fixSalary) {
        this.fixSalary = fixSalary;
    }

    @Override
    public double getMonthSalary() {
        double percentOfMoneyUp = 0.05;
        return fixSalary + moneyUp * percentOfMoneyUp;
    }
}
