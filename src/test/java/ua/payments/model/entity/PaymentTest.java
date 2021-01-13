package ua.payments.model.entity;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.payments.model.entity.enums.Status;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PaymentTest {
    private static Payment payment;

    @BeforeClass
    public static void beforeClass(){
        payment = new Payment();
    }

    @Test
    public void getId() {
        payment.setId(1);
        assertEquals(1, payment.getId());
    }

    @Test
    public void setId() {
        payment.setId(2);
        assertEquals(2, payment.getId());
    }

    @Test
    public void getSum() {
        final BigDecimal expected = BigDecimal.valueOf(1000);
        payment.setSum(expected);
        assertEquals(expected, payment.getSum());
    }

    @Test
    public void setSum() {
        final BigDecimal expected = BigDecimal.valueOf(1000.99);
        payment.setSum(expected);
        assertEquals(expected, payment.getSum());
    }

    @Test
    public void getComment() {
        payment.setComment("comment-1");
        assertEquals("comment-1", payment.getComment());
    }

    @Test
    public void setComment() {
        payment.setComment("comment-2");
        assertEquals("comment-2", payment.getComment());
    }

    @Test
    public void getStatus() {
        payment.setStatus(Status.PAID);
        assertEquals(Status.PAID, payment.getStatus());
    }

    @Test
    public void setStatus() {
        payment.setStatus(Status.PAID);
        assertNotEquals(Status.PREPARED, payment.getStatus());
    }

    @Test
    public void getLastModified() {
        final Timestamp expected = Timestamp.valueOf(LocalDateTime.now());
        payment.setLastModified(expected);
        assertEquals(expected, payment.getLastModified());
    }

    @Test
    public void setLastModified() {
        final Timestamp expected = Timestamp.valueOf(LocalDateTime.now());
        payment.setLastModified(expected);
        assertEquals(expected, payment.getLastModified());
    }

    @Test
    public void getAccount() {
        Account account = new Account();
        payment.setAccount(account);
        assertEquals(account, payment.getAccount());
    }

    @Test
    public void setAccount() {
        Account account = new Account();
        payment.setAccount(account);
        assertEquals(account, payment.getAccount());
    }

    @Test
    public void getCard() {
        Card card = new Card();
        payment.setCard(card);
        assertEquals(card, payment.getCard());
    }

    @Test
    public void setCard() {
        Card card2 = new Card();
        payment.setCard(card2);
        assertEquals(card2, payment.getCard());
    }
}