import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Loader {
    public static void main(String[] args) throws IOException {
        //Sample      +0 123 456-78-90
        for (; ; ) {
            System.out.println("Please, type telephone number or name:");
            String input = (new BufferedReader(new InputStreamReader(System.in))).readLine();
            String number = input.trim().replaceAll("\\D","");
            System.out.println(number);
        }
    }
}

