package ua.payments.exception;

/**
 * This class informs about exception during encrypting of password
 *
 * @author Roman Skab
 * @version 1.0
 */
public class PasswordEncryptException extends RuntimeException{
    public PasswordEncryptException(String message) {
        super(message);
    }

    public PasswordEncryptException(String message, Throwable cause) {
        super(message, cause);
    }
}
