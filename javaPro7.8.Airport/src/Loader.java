import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.util.Calendar;
import java.util.Date;

public class Loader {
    public static void main(String[] args) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        Date afterTwoHour = calendar.getTime();

        Airport airport = Airport.getInstance();
        airport.getTerminals()
                .forEach(terminal -> terminal.getFlights().stream()
                        .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE)
                                && flight.getDate().after(currentDate)
                                && flight.getDate().before(afterTwoHour))
                        .forEach(flight -> System.out.print(flight.getDate() + " / " + flight.getAircraft().getModel() + "\n")));
    }
}