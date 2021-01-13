package ua.payments.model.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptorTest {

    @Test(expected = NullPointerException.class)
    public void should_throw_NullPointerException_when_param_is_null() {
        Encryptor.encrypt(null);
    }

    @Test
    public void encrypt(){
        assertEquals("341a62a8fd29dff8fc5eaf45f624fc71", Encryptor.encrypt("admin"));
    }
}