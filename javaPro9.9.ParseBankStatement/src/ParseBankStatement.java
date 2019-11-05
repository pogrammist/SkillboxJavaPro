import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ParseBankStatement {
    final static String GREETING = "- Я знаю Лямбда выражения! \n- Ну-ка покажи!";

    private static Path parseFile = Paths.get("data/movementList.csv");
    private static String dateFormat = "dd.MM.yy";

    public static void main(String[] args) throws IOException {
        System.out.println(GREETING);

        Statement statement = new Statement(parseFile);

        System.out.println("\nCurrent income | "
                + statement.getStatements().stream()
                .mapToDouble(ItemStatement::getIncome).sum());

        System.out.println("\nCurrent withdraw | "
                + statement.getStatements().stream()
                .mapToDouble(ItemStatement::getWithdraw).sum());

        System.out.println("\nReport of incomes: \nContractor | Income");
        statement.getStatements().stream()
                .filter(itemStatement -> itemStatement.getIncome() != 0)
                .collect(Collectors.groupingBy(
                        ItemStatement::getContractor,
                        Collectors.summingDouble(ItemStatement::getIncome)))
                .forEach((key, sum) -> System.out.println(key + " | " + sum));

        System.out.println("\nReport of withdraws: \nContractor | Withdraw");
        statement.getStatements().stream()
                .filter(itemStatement -> itemStatement.getWithdraw() != 0)
                .collect(Collectors.groupingBy(
                        ItemStatement::getContractor,
                        Collectors.summingDouble(ItemStatement::getWithdraw)))
                .forEach((key, sum) -> System.out.println(key + " | " + sum));

        System.out.println("\nSummary report: \nContractor | Income | Withdraw");
        statement.getStatements().stream()
                .collect(Collectors.groupingBy(
                        ItemStatement::getContractor,
                        Collectors.toSet()))
                .forEach((key, itemStatements) -> itemStatements
                        .forEach(itemStatement -> System.out.println(key + " | " + itemStatement.getIncome() + " | " + itemStatement.getWithdraw())));
    }
}
