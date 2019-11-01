public class Loader {

    private static final int PATIENTS = 30;
    private static final int START_TEMPERATURE = 32;
    private static final int END_TEMPERATURE = 40;
    private static final double MIN_NORMAL_TEMPERATURE = 36.2;
    private static final double MAX_NORMAL_TEMPERATURE = 36.9;

    public static void main(String[] args) {
        float[] temperatures = new float[PATIENTS];
        float sumTemperatures = 0;
        float averageTemperature = 0;
        int countTruePatients = 0;
        for (int i = 0; i < temperatures.length; i++) {
            temperatures[i] = (float) Math.random() * (END_TEMPERATURE - START_TEMPERATURE) + START_TEMPERATURE;
            if (temperatures[i] > MIN_NORMAL_TEMPERATURE && temperatures[i] < MAX_NORMAL_TEMPERATURE) {
                System.out.println("Пациент № " + (i + 1) + " с температурой " + temperatures[i] + " \"ЗДОРОВ\"");
                countTruePatients++;
            }
            sumTemperatures += temperatures[i];
            averageTemperature = sumTemperatures / (i + 1);
        }
        System.out.println("Итого здоровых: " + countTruePatients);
        System.out.println("Средняя температура по больнице: " + averageTemperature);
    }
}
