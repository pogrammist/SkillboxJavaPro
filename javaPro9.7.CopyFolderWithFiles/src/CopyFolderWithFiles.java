import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CopyFolderWithFiles {
    //Hi from https://stackoverflow.com/questions/29076439/java-8-copy-directory-recursively/34254130 :)
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("This app will copying folder with included files");
        Scanner input = new Scanner(System.in);
        for (; ; ) {
            Path src = takePath("type source path to copy :");
            Path dest = takePath("type destination path to copy:");
            Files.walk(src).forEach(source -> {
                try {
                    Path destination = dest.resolve(src.relativize(source));
                    System.out.println(destination);
                    if (Files.isDirectory(source)) {
                        if (Files.notExists(destination)) {
                            System.err.println("creating directory: " + destination);
                            Files.createDirectory(destination);
                        }
                    } else {
                        System.out.println("copying file: " + source);
                        Files.copy(source, destination, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            System.out.println("Directory is copied");
        }
    }

    private static Path takePath(String message) {
        for (; ; ) {
            System.out.println(message);
            String line = scanner.nextLine().trim();
            Path path = Paths.get(line).normalize();
            if (Files.isDirectory(path)) {
                return path;
            }
            System.out.println("Invalid path");
        }
    }
}
