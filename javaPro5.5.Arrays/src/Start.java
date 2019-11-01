public class Start {
    private static String[][] xs = {
            {"x", " ", " ", " ", " ", " ", "x"},
            {" ", "x", " ", " ", " ", "x", " "},
            {" ", " ", "x", " ", "x", " ", " "},
            {" ", " ", " ", "x", " ", " ", " "},
            {" ", " ", "x", " ", "x", " ", " "},
            {" ", "x", " ", " ", " ", "x", " "},
            {"x", " ", " ", " ", " ", " ", "x"}
    };

    public static void main(String[] args) {
        for (int y = 0; y < xs.length; y++) {
            for (int x = 0; x < xs.length; x++) {
                System.out.print(xs[y][x]);
            }
            System.out.println();
        }
    }
}
