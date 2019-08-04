public class Starter {
    public static void main(String[] args) {

        //Print ticket of 200000 to 235000 without of 210001 to 219999
        int ticketNumber = 200000;

//        for (int i = ticketNumber; i <= 235000; i++){
//            if (i == 210001) {
//                i = 220000;
//            }
//            System.out.println("Ticket: " + i);
//        }

        while (ticketNumber <= 235000) {
            if (ticketNumber == 210001) {
                ticketNumber = 220000;
            }
                System.out.println("Ticket: " + ticketNumber);
            ticketNumber++;
        }
    }
}
