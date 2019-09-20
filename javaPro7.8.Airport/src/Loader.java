import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.util.Date;

public class Loader {
    public static void main(String[] args) {
        Date currentDate = new Date();
        Date afterTwoHour = new Date();
        afterTwoHour.setHours(currentDate.getHours() + 2);

        Airport airport = Airport.getInstance();
        airport.getTerminals()
                .forEach(terminal -> terminal.getFlights().stream()
                        .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE)
                                && flight.getDate().after(currentDate)
                                && flight.getDate().before(afterTwoHour))
                        .forEach(flight -> System.out.print(flight.getDate() + " / " + flight.getAircraft().getModel() + "\n")));
    }
}