package errorhandling;

public class MissingInputException extends Exception{
    int statusCode;

    public MissingInputException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
