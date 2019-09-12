package Tesla;

import java.util.*;
import java.util.stream.Collectors;

public class Company {
    private TreeSet<Employee> employees;
    private double income;
    private double fixedSalary;
    private int intSalesManager;
    private int intTopManager;
    private int intClerk;

    public Company(int employee) {
        employees = new TreeSet<>();
        int intEmployees = 0;
        while (intEmployees != employee) {
            intSalesManager = (int) (Math.random() * employee);
            intTopManager = (int) (Math.random() * employee);
            intClerk = (int) (Math.random() * employee);
            intEmployees = +intSalesManager + intTopManager + intClerk;
        }
        for (int item = 0; item < intSalesManager; item++) {
            addSalesManager();
        }
        for (int item = 0; item < intTopManager; item++) {
            addTopManager();
        }
        for (int item = 0; item < intClerk; item++) {
            addClerk();
        }
        fixedSalary = income * 0.01;
        employees.forEach(e -> e.setFixSalary(fixedSalary));
        employees.stream()
                .filter(e -> e instanceof TopManager)
                .forEach(e -> ((TopManager) e).setIncome(income));
    }

    private void addSalesManager() {
        SalesManager salesManager = new SalesManager();
        income = salesManager.getMoneyUp();
        employees.add(new SalesManager());
    }

    private void addTopManager() {
        employees.add(new TopManager());
    }

    private void addClerk() {
        employees.add(new Clerk());
    }

    public List<Employee> getTopSalaryStaff(int count) {
        //to study
        List<Employee> list = new ArrayList<>(employees);
        Collections.sort(list, new MaxSalaryComparator());
        return list.stream().limit(count).collect(Collectors.toList());
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        //to study
        List<Employee> list = new ArrayList<>(employees);
        Collections.sort(list, new MinSalaryComparator());
        return list.stream().limit(count).collect(Collectors.toList());
    }

    public double getIncome() {
        return income;
    }

    public double getFixedSalary() {
        return fixedSalary;
    }

    public void removeEmployee() {
        int maxIndex = employees.size();
        int removeIndex = (int) (Math.random() * maxIndex);
        employees.remove(removeIndex);
    }

    public void showEmployees() {
        int number = 1;
        for (Employee item : employees) {
            System.out.println(number++ + " - " + item + " - " + item.getMonthSalary());
        }
    }
}
