import Tesla.Company;

public class Main {
    public static void main(String[] args) {
        Company company = new Company(270);
        company.showEmployees();
        company.getTopSalaryStaff(5).stream().forEach(e -> System.out.println("top " + e.getMonthSalary()));
        company.getLowestSalaryStaff(5).stream().forEach(e -> System.out.println("low " + e.getMonthSalary()));
    }
}
