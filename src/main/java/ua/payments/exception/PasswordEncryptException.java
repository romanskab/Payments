package ua.payments.exception;

public class PasswordEncryptException extends RuntimeException{
    public PasswordEncryptException(String message) {
        super(message);
    }

    public PasswordEncryptException(String message, Throwable cause) {
        super(message, cause);
    }
}
