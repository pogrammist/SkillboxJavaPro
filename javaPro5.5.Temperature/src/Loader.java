public class Loader {

    private static int patients = 30;
    private static int startTemperature = 32;
    private static int endTemperature = 40;
    private static double minNormalTemperature = 36.2;
    private static double maxNormalTemperature = 36.9;
    private static float[] temperatures = new float[patients];

    public static void main(String[] args) {
        float sumTemperatures = 0;
        float averageTemperature = 0;
        for (int i = 0; i < temperatures.length; i++) {
            temperatures[i] = (float) Math.random() * (endTemperature - startTemperature) + startTemperature;
            if (temperatures[i] > minNormalTemperature && temperatures[i] < maxNormalTemperature) {
                System.out.println("Пациент № " + (i + 1) + " с температурой " + temperatures[i] + " \"ЗДОРОВ\"");
            }
            sumTemperatures += temperatures[i];
            averageTemperature = sumTemperatures / (i + 1);
        }
        System.out.println("Средняя температура по больнице: " + averageTemperature);
    }
}
