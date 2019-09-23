public class InputStringException extends Exception {
    private String detail;

    InputStringException(String a) {
        detail = a;
    }

    public String getMessage() {
        return "MyException [" + detail + "]";
    }
}