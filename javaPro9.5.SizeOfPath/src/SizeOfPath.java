import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;

public class SizeOfPath {
    //https://stackoverflow.com/questions/2149785/get-size-of-folder-or-file

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        for (; ; ) {
            System.out.println("type path");
            String inputPath = input.nextLine();
            Path path = Paths.get(inputPath);
            try {
                long size = Files.walk(path)
                        .map(f -> f.toFile())
                        .filter(f -> f.isFile())
                        .mapToLong(f -> f.length()).sum();
                System.out.println(getReadableSize(size));
            } catch (Exception e) {
                System.out.println("Invalid path or " + e);
            }
        }
    }

    public static String getReadableSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
