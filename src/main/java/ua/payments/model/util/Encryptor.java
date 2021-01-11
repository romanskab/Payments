package ua.payments.model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.exception.PasswordEncryptException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class is used for encrypting of password
 *
 * @author Roman Skab
 * @version 1.0
 */
public class Encryptor {
    private static final Logger logger = LogManager.getLogger(Encryptor.class);

    private static final String KEY_WORD = "keyWord";

    /**
     * This method encrypts a password with MD5 algorithm.
     *
     * @param password contains the value to crypt.
     * @return a String representing the encrypted value
     * @throws PasswordEncryptException - if can not encrypt.
     */
    public static String encrypt(String password) {
        byte[] salt = getSalt();
        try {
            return getSecurePassword(password, salt);
        } catch (NoSuchAlgorithmException e) {
            PasswordEncryptException exception = new PasswordEncryptException("Can not encrypt password", e);
            logger.error("encrypt() failed", exception);
            throw exception;
        }
    }

    /**
     * This method encrypts a password with MD5 algorithm.
     *
     * @param passwordToHash contains the value to crypt.
     * @param salt contains byte-array of secret-word.
     * @return a String representing the encrypted value
     * @throws NoSuchAlgorithmException - if can not encrypt.
     */
    private static String getSecurePassword(String passwordToHash, byte[] salt) throws NoSuchAlgorithmException {
        String generatedPassword;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt);
        byte[] bytes = md.digest(passwordToHash.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
    }

    /**
     * This method returns byte-array of secret-word.
     */
    private static byte[] getSalt() {
        return KEY_WORD.getBytes();
    }
}
