package ua.payments.exception;

/**
 * This exception-class informs about forbidden access to the resource
 *
 * @author Roman Skab
 * @version 1.0
 */
public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message) {
        super(message);
    }
}
