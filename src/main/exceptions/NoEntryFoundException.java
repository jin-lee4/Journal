package exceptions;

public class NoEntryFoundException extends Exception {

    public NoEntryFoundException() {}

    public NoEntryFoundException(String msg) {
        super(msg);
    }
}
