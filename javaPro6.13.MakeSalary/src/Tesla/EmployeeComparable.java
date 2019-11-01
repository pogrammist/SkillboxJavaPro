package Tesla;

public abstract class EmployeeComparable implements Employee, Comparable<Employee> {
    public int compareTo(Employee employee) {
        if (getMonthSalary() > employee.getMonthSalary()) {
            return 1;
        }
        if (getMonthSalary() < employee.getMonthSalary()) {
            return -1;
        }
        return 0;
    }
}
