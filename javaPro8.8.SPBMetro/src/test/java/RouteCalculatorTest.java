import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RouteCalculatorTest extends TestCase {

    private RouteCalculator calculator;
    private StationIndex stationIndex = new StationIndex();
    private List<Station> route;

    private Line x = new Line(1, "Ночная");
    private Line y = new Line(2, "Утренняя");
    private Line z = new Line(3, "Дневная");
    private Line u = new Line(4, "Вечерняя");

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        x.addStation(new Station("x0", x));
        x.addStation(new Station("x1", x));
        x.addStation(new Station("x2", x));
        x.addStation(new Station("x3", x));
        x.addStation(new Station("x4", x));
        x.addStation(new Station("x5", x));
        x.addStation(new Station("x6", x));

        y.addStation(new Station("y6", y));
        y.addStation(new Station("y7", y));
        y.addStation(new Station("y8", y));
        y.addStation(new Station("y9", y));
        y.addStation(new Station("y10", y));
        y.addStation(new Station("y11", y));
        y.addStation(new Station("y12", y));

        z.addStation(new Station("z2", z));
        z.addStation(new Station("z8", z));

        u.addStation(new Station("u4", u));
        u.addStation(new Station("u6", u));
        u.addStation(new Station("u8", u));
        u.addStation(new Station("u10", u));

        x.getStations().forEach(station -> stationIndex.addStation(station));
        y.getStations().forEach(station -> stationIndex.addStation(station));
        z.getStations().forEach(station -> stationIndex.addStation(station));
        u.getStations().forEach(station -> stationIndex.addStation(station));

        stationIndex.addLine(x);
        stationIndex.addLine(y);
        stationIndex.addLine(z);
        stationIndex.addLine(u);

        List<Station> connectionStations = new ArrayList<>();

        connectionStations.add(x.getStations().get(6));
        connectionStations.add(y.getStations().get(0));
        stationIndex.addConnection(connectionStations);
        connectionStations.clear();

        connectionStations.add(x.getStations().get(2));
        connectionStations.add(z.getStations().get(0));
        stationIndex.addConnection(connectionStations);
        connectionStations.clear();

        connectionStations.add(y.getStations().get(2));
        connectionStations.add(z.getStations().get(1));
        stationIndex.addConnection(connectionStations);
        connectionStations.clear();

        connectionStations.add(x.getStations().get(4));
        connectionStations.add(u.getStations().get(0));
        stationIndex.addConnection(connectionStations);
        connectionStations.clear();

        connectionStations.add(y.getStations().get(4));
        connectionStations.add(u.getStations().get(2));
        stationIndex.addConnection(connectionStations);

        calculator = new RouteCalculator(stationIndex);
    }

    public void testWillReturnDuration() {
        route = calculator.getShortestRoute(x.getStations().get(3), y.getStations().get(3));
        double actual = RouteCalculator.calculateDuration(route);
        double expect = 18.5;
        assertEquals(expect, actual);
    }

    public void testWillReturnRouteOnTheLine() {
        route = calculator.getRouteOnTheLine(x.getStations().get(5), x.getStations().get(1));
        List<String> actual = route.stream().map(station -> station.getName()).collect(Collectors.toList());
        List<String> expected = Arrays.asList("x5", "x4", "x3", "x2", "x1");
        assertEquals(expected, actual);
    }

    public void testRouteWithOneConnection() {
        route = calculator.getRouteWithOneConnection(x.getStations().get(0), y.getStations().get(3));
        List<String> actual = route.stream().map(station -> station.getName()).collect(Collectors.toList());
        List<String> expected = Arrays.asList("x0", "x1", "x2", "x3", "x4", "x5", "x6", "y6", "y7", "y8", "y9");
        assertEquals(expected, actual);
    }

    public void testRouteWithTwoConnections() {
        route = calculator.getRouteWithTwoConnections(x.getStations().get(0), y.getStations().get(6));
        List<String> actual = route.stream().map(station -> station.getName()).collect(Collectors.toList());
        List<String> expected = Arrays.asList("x0", "x1", "x2", "z2", "z8", "y8", "y9", "y10", "y11", "y12");
        assertEquals(expected, actual);
    }
}
