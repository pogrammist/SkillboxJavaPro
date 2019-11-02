import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CopyFolderWithFiles {
    //Hi from https://stackoverflow.com/questions/29076439/java-8-copy-directory-recursively/34254130 :)
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("This app will copying folder with included files");
        Scanner input = new Scanner(System.in);
        for (; ; ) {
            try {
                Path src = takePath("type source path to copy :");
                Path dest = takePath("type destination path to copy:");
                copyFolder(src, dest);
                System.out.println("Directory is copied");
            } catch (Exception e) {
                System.out.println(e.fillInStackTrace());
            }
        }
    }

    private static void copyFolder(Path src, Path dest) throws IOException {
        Files.walk(src)
                .forEach(source -> copy(source, dest.resolve(src.relativize(source))));
    }

    private static void copy(Path src, Path dest) {
        try {
            Files.copy(src, dest, REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Path takePath(String message) {
        for (; ; ) {
            System.out.println(message);
            String line = scanner.nextLine().trim();
            Path path = Paths.get(line);
            if (Files.isDirectory(path)){
                return path;
            }
            System.out.println("Invalid path");
        }
    }
}
