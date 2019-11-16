import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ParseHtml {
    // https://stackoverflow.com/questions/921262/how-to-download-and-save-a-file-from-internet-using-java
    // https://www.baeldung.com/java-url
    // http://qaru.site/questions/28326/how-to-combine-paths-in-java

    private static final String DESTINATION = "data";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("This app will use jsoap for parse html");
        Scanner input = new Scanner(System.in);
        for (; ; ) {
            URL url = takeUrl();
            Document document = Jsoup.connect(String.valueOf(url)).get();
            Elements elements = document.getElementsByTag("img");
            elements.forEach(element -> {
                try {
                    System.out.println(element.absUrl("src"));
                    download(element.absUrl("src"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void download(String src) throws Exception {
        try (InputStream source = new URL(src).openStream()) {
            URL srcUrl = new URL(src);
            Files.copy(source, Paths.get(DESTINATION, Paths.get(srcUrl.getFile()).getFileName().toString()));
        }
//        URL srcUrl = new URL(src);
//        Path srcPath = Paths.get(srcUrl.getFile().substring(1));
//        Path destPath = Paths.get(DESTINATION, Paths.get(srcUrl.getFile()).getFileName().toString());
//        Files.copy(srcPath, destPath, REPLACE_EXISTING);
    }

    private static URL takeUrl() {
        for (; ; ) {
            System.out.println("type link to html page with protocol type to parse:");
            String inputURL = scanner.nextLine().trim();
            try {
                URL url = new URL(inputURL);
                return url;
            } catch (Exception e) {
                System.err.println("Invalid URL or " + e);
            }
        }
    }
}
