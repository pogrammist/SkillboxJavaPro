import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseHtmlTest {

    @Test
    public void testWillReturnCorrectRelativeUrlFromBaseUrl() throws MalformedURLException {
        URL baseRelativeUrl = new URL("https://static.rambler.ru");
        String absoluteUrl = "icons/topline/ui/search.svg";
        URL relativeUrl = new URL(baseRelativeUrl, absoluteUrl);
        String expected = "https://static.rambler.ru/icons/topline/ui/search.svg";
        assertEquals(expected, relativeUrl.toString());
    }

    @Test
    public void testWillReturnPathOfTheResourceFromRelativeUrl() throws MalformedURLException {
        URL relativeUrl = new URL("https://static.rambler.ru/icons/topline/ui/search.svg");
        Path pathOfTheResource = Paths.get(relativeUrl.getPath());
        Path expected = Paths.get("/icons/topline/ui/search.svg");
        assertEquals(expected, pathOfTheResource);
    }

    @Test
    public void testWillReturnAbsolutePathFromRelativeUrlAndAbsolutePath() throws MalformedURLException {
        URL relativeUrl = new URL("https://static.rambler.ru/icons/topline/ui/search.svg");
        String pathOfTheResource = relativeUrl.getPath();
        String absolutePath = "data";
        Path newAbsolutePath = Paths.get(absolutePath, pathOfTheResource);
        String expected = "data/icons/topline/ui/search.svg";
        assertEquals(expected, newAbsolutePath.toString());
    }
}
