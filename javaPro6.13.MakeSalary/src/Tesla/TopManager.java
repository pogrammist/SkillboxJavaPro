package Tesla;

public class TopManager extends EmployeeComparable implements Employee {
    private double fixSalary;
    private double bonus = 0;

    public void setFixSalary(double fixSalary) {
        this.fixSalary = fixSalary;
    }

    public void setIncome(double income) {
        if (income > 10000000) {
            double percentOfBonus = 0.1;
            bonus = income * percentOfBonus;
        }
    }

    @Override
    public double getMonthSalary() {
        return fixSalary + bonus;
    }
}
