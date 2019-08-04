import java.util.Scanner;

public class Main {
    //  Объявляем кучу переменных и сприсваиваем значения

    //  Минимальный доход
    private static int minIncome = 200000;
    //  Максимальный доход
    private static int maxIncome = 900000;

    //  Аренда офиса
    private static int officeRentCharge = 140000;
    //  Телефония
    private static int telephonyCharge = 12000;
    //  Интернет
    private static int internetAccessCharge = 7200;

    //  Зарплата помощника
    private static int assistantSalary = 45000;
    //  Зарплата Финансовый менеджер
    private static int financeManagerSalary = 90000;

    //  Основной налог
    private static double mainTaxPercent = 0.24;
    //  Процент менеджера
    private static double managerPercent = 0.15;

    //  Минимальная сумма инвестиций
    private static double minInvestmentsAmount = 100000;

    //  Минимальный доход c выходом на инвестирование
    private static Double minTrueIncome =
            (minInvestmentsAmount + calculateFixedCharges() - mainTaxPercent * calculateFixedCharges())
                    / (1 - managerPercent - mainTaxPercent + managerPercent * mainTaxPercent);

    //  Основной метод
    public static void main(String[] args) {
        //  Цикл по расчету бюджета и т.д.
        while (true) {
            //  Ввод необходимых данных
            System.out.println("Введите сумму доходов компании за месяц " + "(от 200 до 900 тысяч рублей): ");
            int income = (new Scanner(System.in)).nextInt();

            //  Проверка
            if (!checkIncomeRange(income)) {
                continue;
            }
            //  Зарплата менеджера
            double managerSalary = income * managerPercent;
            //  Чистый доход
            double pureIncome = income - managerSalary - calculateFixedCharges();
            //  Сумма налога
            double taxAmount = mainTaxPercent * pureIncome;
            //  Чистый доход после уплаты налогов
            double pureIncomeAfterTax = pureIncome - taxAmount;

            //  Возможность сделать инвестиции
            boolean canMakeInvestments = pureIncomeAfterTax >= minInvestmentsAmount;

            System.out.println("Зарплата менеджера: " + managerSalary);
            System.out.println("Общая сумма налогов: " + (taxAmount > 0 ? taxAmount : 0));
            System.out.println("Компания может инвестировать: " + (canMakeInvestments ? "да" : "нет"));
            if (pureIncome < 0) {
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");
            } else if (pureIncome == 0) {
                System.out.println("Доходы покрыли расхды! Точка безубыточности достигнута!");
            }
            System.out.println("Компания сможет инвистировать с доходов  " + minTrueIncome);
        }
    }

    //  Метод проверки введенных данных
    private static boolean checkIncomeRange(int income) {
        if (income < minIncome) {
            System.out.println("Доход меньше нижней границы");
            return false;
        }
        if (income > maxIncome) {
            System.out.println("Доход выше верхней границы");
            return false;
        }
        return true;
    }

    //  Метод подсчета фиксированных расходов
    private static int calculateFixedCharges() {
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary;
    }
}
