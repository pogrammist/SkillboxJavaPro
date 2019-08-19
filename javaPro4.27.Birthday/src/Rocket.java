import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Rocket {

    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("- dd.MM.yyyy - E", Locale.US);
    static final LocalDate BIRHDAY = LocalDate.of(1986, 6, 22);
    static final LocalDate NOW = LocalDate.now();

    public static void main(String[] args) {

//        Samples
//        0 ­ 13.02.1989 ­ Mon
//        1 ­ 13.02.1990 - Tue

        {
            int rowNo = 0;
            for (LocalDate d = BIRHDAY; d.isBefore(NOW); d = d.plusYears(1)) {
                System.out.printf("%2d %s%n", rowNo++, d.format(DATE_FORMAT));
            }
        }
    }
}
