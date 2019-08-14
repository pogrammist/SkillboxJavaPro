public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.count += 7843;

        System.out.println(sumDigits(container.count));
    }

    public static Integer sumDigits(Integer number) {
        //@TODO: write code here
        String stringOfNumber = number.toString();
        int countDigits = stringOfNumber.length();
        int sumOfdigits = 0;
        for (int i = 0; i < countDigits; i++){
            int item =  Character.getNumericValue(stringOfNumber.charAt(i));
            sumOfdigits += item;
        }
        return sumOfdigits;
    }
}
