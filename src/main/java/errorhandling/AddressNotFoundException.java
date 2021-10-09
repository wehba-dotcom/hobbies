package errorhandling;

  public class AddressNotFoundException extends Exception{
    int statusCode;
    public AddressNotFoundException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }
}
