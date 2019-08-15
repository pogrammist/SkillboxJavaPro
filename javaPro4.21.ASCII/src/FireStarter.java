public class FireStarter {
    public static void main(String[] args) {
        // English Chars
        for (int x = 65; x <= 122; x++) {
            if (x > 90 && x < 97) {
                continue;
            } else {
                System.out.println((char) x + " - " + x);
            }
        }
    }
}