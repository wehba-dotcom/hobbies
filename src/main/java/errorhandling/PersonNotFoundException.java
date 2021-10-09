package errorhandling;

public class PersonNotFoundException extends Exception{

    int statusCode;

    public PersonNotFoundException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
