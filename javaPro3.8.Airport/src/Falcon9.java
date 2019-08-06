import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;

import java.util.List;

public class Falcon9 {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Aircraft> list = airport.getAllAircrafts();
        for (Aircraft item : list) {
            System.out.println(item);
        }
    }
}
