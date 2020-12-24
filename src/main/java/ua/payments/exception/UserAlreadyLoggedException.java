package ua.payments.exception;

public class UserAlreadyLoggedException extends RuntimeException {
    public UserAlreadyLoggedException(String message) {
        super(message);
    }
}
