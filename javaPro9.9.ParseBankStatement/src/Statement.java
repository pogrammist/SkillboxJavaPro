import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Statement {
    private ArrayList<ItemStatement> statements = new ArrayList<>();

    public Statement(Path parseFile) throws IOException {
        List<String> lines = Files.readAllLines(parseFile);

        List<String[]> ListArrayLines = lines.stream()
                .filter(line -> !line.equals(lines.get(0)))
                .map(line -> line.split(",", 8))
                .collect(Collectors.toList());

        ListArrayLines.forEach(strings -> statements.add(new ItemStatement(
                strings[0].toString(),
                new BigInteger(strings[1]),
                strings[2].toString(),
                LocalDate.parse(strings[3], DateTimeFormatter.ofPattern("dd.MM.yy")),
                strings[4].toString(),
                getСontractor(strings[5]),
                Double.parseDouble(strings[6]),
                Double.parseDouble(strings[7].replaceAll("\"([^\"]*),([^\"]*)\"", "$1.$2"))
        )));
    }

    private static String getСontractor(String string) {
        String[] firstSplit = string.split("\\\\|/");
        String[] secondSplit = firstSplit[firstSplit.length - 1].split("\\d+\\.");
        String contractor = secondSplit[0].trim();
        return contractor;
    }

    public ArrayList<ItemStatement> getStatements() {
        return statements;
    }
}
