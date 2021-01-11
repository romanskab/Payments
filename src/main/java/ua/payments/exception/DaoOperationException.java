package ua.payments.exception;

/**
 * This exception-class informs about exceptions in DAO-classes.
 *
 * @author Roman Skab
 * @version 1.0
 */
public class DaoOperationException extends RuntimeException {
    public DaoOperationException(String message) {
        super(message);
    }

    public DaoOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
