package errorhandling;

public class HoppyNotFoundException extends Exception{

    int statusCode;

    public HoppyNotFoundException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
