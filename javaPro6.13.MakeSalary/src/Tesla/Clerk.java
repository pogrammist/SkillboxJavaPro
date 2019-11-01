package Tesla;

public class Clerk extends EmployeeComparable implements Employee {
    private double fixSalary;

    public void setFixSalary(double fixSalary) {
        this.fixSalary = fixSalary;
    }

    @Override
    public double getMonthSalary() {
        return fixSalary;
    }
}
