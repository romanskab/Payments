package ua.payments.exception;

public class AccountIsBlockedException extends RuntimeException {
    public AccountIsBlockedException(String message) {
        super(message);
    }
}
