package ua.payments.exception;

/**
 * This exception-class informs that user not found in the database
 *
 * @author Roman Skab
 * @version 1.0
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
