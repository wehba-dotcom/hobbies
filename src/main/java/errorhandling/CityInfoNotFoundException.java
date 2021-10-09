package errorhandling;

public class CityInfoNotFoundException extends Exception{

    int statusCode;

    public CityInfoNotFoundException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
