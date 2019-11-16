import static java.lang.CharSequence.compare;

public class Station implements Comparable<Station>{
    private String line;
    private String name;

    public Station(String line, String name) {
        this.line = line;
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Station station) {
        return compare(name, station.getName());
    }
}