package backend.exceptions;

public class ConnectionFailureException extends RuntimeException {
    public ConnectionFailureException(String message) {
        super(message);
    }
}
