package exceptions;

public class BrowserNotSupportException extends RuntimeException {

    public BrowserNotSupportException(String browserName) {
        super(String.format("Browser %s not supported", browserName));
    }

}
