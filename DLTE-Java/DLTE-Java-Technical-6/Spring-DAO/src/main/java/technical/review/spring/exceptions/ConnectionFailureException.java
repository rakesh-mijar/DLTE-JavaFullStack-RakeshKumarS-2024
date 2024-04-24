package technical.review.spring.exceptions;

public class ConnectionFailureException extends RuntimeException {
    public ConnectionFailureException(String message) {
        super(message);
    }
}

