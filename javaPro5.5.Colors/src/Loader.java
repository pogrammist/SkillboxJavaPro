public class Loader {
    public static void main(String[] args) {
        String text = "Каждый охотник желает знает, где живет фазан";

        String[] colors = text.split(",?\\s+");

        for (int i = 0; i < colors.length / 2; i++) {
            String cache = colors[i];
            colors[i] = colors[colors.length - i - 1];
            colors[colors.length - i - 1] = cache;
        }

        for (String item : colors) {
            System.out.println(item);
        }
    }
}
