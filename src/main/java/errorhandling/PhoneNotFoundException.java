package errorhandling;

public class PhoneNotFoundException extends Exception{

    int statusCode;

    public PhoneNotFoundException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
