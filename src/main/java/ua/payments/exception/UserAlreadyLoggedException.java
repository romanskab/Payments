package ua.payments.exception;

/**
 * This exception-class informs that user is already logged
 *
 * @author Roman Skab
 * @version 1.0
 */
public class UserAlreadyLoggedException extends RuntimeException {
    public UserAlreadyLoggedException(String message) {
        super(message);
    }
}
