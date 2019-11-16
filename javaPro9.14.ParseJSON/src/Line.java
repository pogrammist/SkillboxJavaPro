import java.util.List;

import static java.lang.CharSequence.compare;

public class Line implements Comparable<Line> {

    private String number;
    private String color;
    private String name;
    private List<Station> stations;

    public Line(String number, String color, String name, List<Station> stations) {
        this.number = number;
        this.color = color;
        this.name = name;
        this.stations = stations;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    public int compareTo(Line line) {
        return compare(name, line.getName());
    }
}