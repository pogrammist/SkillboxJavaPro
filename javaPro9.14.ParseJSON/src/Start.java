import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Start {
    private static final String SOURCE = "https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";
    public static final String PATH = "data/mscmetro.json";
    private static String column;
    private static String color;
    private static String station;
    private static String line;
    private static List<List> rows = new ArrayList<>();
    private static List<Station> stationsOfHtml = new ArrayList<>();
    private static TreeSet<Line> linesOfHtml = new TreeSet<>();
    private static TreeMap<Station, List<Station>> connectionsOfHtml = new TreeMap<>();
    private static List<Station> linkStations = new ArrayList<>();

    public static void main(String[] args) throws IOException, ParseException {

        Document document = Jsoup.connect("https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена").maxBodySize(0).get();
        Elements tables = document.getElementsByClass("standard sortable");

        //parse tables
        tables.forEach(table -> {
            // table
            table.select("tr")
                    .forEach(tr -> {
                        // row
                        ArrayList row = new ArrayList();
                        color = tr.select("td[style]").attr("style");
                        tr.select("td")
                                .forEach(td -> {
                                    //column
                                    column = td.select("span").text().trim();
                                    station = td.select("a[href]").text().trim();
                                    line = td.select("span[title]").attr("title");
                                    if (row.isEmpty()) {
                                        ArrayList splitColumn = new ArrayList();
                                        row.add(column.split("\\s")[0]);
                                        row.add(color);
                                        row.add(line);
                                    } else if (row.size() == 3 && column.isEmpty()) {
                                        row.add(station);
                                    } else if (row.size() == 3 && station.isEmpty()) {
                                        row.add(column);
                                    } else if (row.size() <= 5) {
                                        row.add(column);
                                    }
                                });
                        if (row.size() != 0) {
                            row.remove(4);
                            rows.add(row);
                        }
                    });
        });

        //print source array
//        rows.stream().forEach(row -> {
//            AtomicInteger item = new AtomicInteger(1);
//            row.forEach(col -> System.out.println((item.getAndIncrement()) + " - " + col));
//        });

        //make list of stations
        rows.stream().forEach(row -> {
            stationsOfHtml.add(
                    new Station(row.get(0).toString(),
                            row.get(3).toString()));
        });

        //make list of lines with stations
        rows.forEach(row -> {
            linesOfHtml.add(new Line(row.get(0).toString(),
                    row.get(1).toString().length() != 18 ? "" : row.get(1).toString().split(":")[1],
                    row.get(2).toString(),
                    stationsOfHtml.stream()
                            .filter(station ->
                                    station.getLine().equals(row.get(0).toString()))
                            .collect(Collectors.toList())));
        });

        //make list of station connection with lines
        rows.stream()
                .filter(row -> row.get(4).toString().length() != 0)
                .forEach(row -> {
//                    System.out.println(row.get(0) + "-" + row.get(3) + " " + row.get(4));
                    Arrays.stream(row.get(4).toString().split("\\s+"))
                            .forEach(link -> {
//                                System.out.println(link);
                                linkStations = new ArrayList<>();
                                rows.stream()
                                        .filter(row1 -> row1.get(4).toString().length() != 0
                                                && row1.get(0).toString().equals(link.trim())
                                                && row1.get(4).toString().equals(row.get(0))
                                        )
                                        .forEach(row1 -> {
//                                            System.out.println(row.get(0) + "-" + row.get(3) + " " + row.get(4) + " / "
//                                                    + row1.get(0) + "-" + row1.get(3) + " " + row1.get(4));
                                            if (!connectionsOfHtml.containsKey(new Station(row1.get(0).toString(),
                                                    row1.get(3).toString()))) {
                                                linkStations.add(new Station(row1.get(0).toString(),
                                                        row1.get(3).toString()));
                                            }
                                        });
                                if (!linkStations.isEmpty()) {
                                    connectionsOfHtml.put(
                                            new Station(row.get(0).toString(),
                                                    row.get(3).toString()),
                                            linkStations);
                                }
                            });
                });

        JSONObject stations = new JSONObject();
        linesOfHtml.forEach(line -> {
            JSONArray jsonStations = new JSONArray();
            line.getStations()
                    .forEach(station -> jsonStations.add(station.getName()));
            stations.put(line.getNumber(), jsonStations);
        });

        JSONArray lines = new JSONArray();
        linesOfHtml.forEach(line -> {
            JSONObject jsonLine = new JSONObject();
            jsonLine.put("number", line.getNumber());
            jsonLine.put("name", line.getName());
            jsonLine.put("color", line.getColor());
            lines.add(jsonLine);
        });

        JSONArray connections = new JSONArray();
        connectionsOfHtml.forEach((station, connectStations) -> {
            JSONArray jsonConnections = new JSONArray();
            JSONObject jsonStation = new JSONObject();
            jsonStation.put("line", station.getLine());
            jsonStation.put("station", station.getName());
            jsonConnections.add(jsonStation);
            connectStations
                    .forEach(connectStation -> {
                        JSONObject jsonConnection = new JSONObject();
                        jsonConnection.put("line", connectStation.getLine());
                        jsonConnection.put("station", connectStation.getName());
                        jsonConnections.add(jsonConnection);
                    });
            connections.add(jsonConnections);
        });

        JSONObject mscmetro = new JSONObject();
        mscmetro.put("stations", stations);
        mscmetro.put("lines", lines);
        mscmetro.put("connections", connections);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Files.write(Paths.get(PATH), gson.toJson(mscmetro).getBytes());

        JSONParser jsonParser = new JSONParser();

        StringBuilder builder = new StringBuilder();
        List<String> strings = Files.readAllLines(Paths.get(PATH));
        strings.forEach(line -> builder.append(line));

        JSONObject jsonData = (JSONObject) jsonParser.parse(builder.toString());
        JSONObject stationsObject = (JSONObject) jsonData.get("stations");
        System.out.println("Stations on lines: ");
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            int stationsOnLineCount = stationsArray.size();
            System.out.println(lineNumberObject + " : " + stationsOnLineCount);
        });
    }
}
