package ua.payments.model.entity;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.payments.model.entity.enums.State;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AccountTest {
    private static Account account;

    @BeforeClass
    public static void beforeClass(){
        account = new Account();
    }

    @Test
    public void getId() {
        account.setId(1);
        assertEquals(1, account.getId());
    }

    @Test
    public void setId() {
        account.setId(1);
        assertEquals(1, account.getId());
    }

    @Test
    public void getBalance() {
        account.setBalance(BigDecimal.valueOf(1000.99));
        assertEquals(BigDecimal.valueOf(1000.99), account.getBalance());
    }

    @Test
    public void setBalance() {
        account.setBalance(BigDecimal.valueOf(9999999999999999999999999999999999999999999999999999999.99));
        assertEquals(BigDecimal.valueOf(9999999999999999999999999999999999999999999999999999999.99), account.getBalance());
    }

    @Test
    public void getState() {
        account.setState(State.BLOCKED);
        assertEquals(State.BLOCKED, account.getState());
    }

    @Test
    public void setState() {
        account.setState(State.UNBLOCKED);
        assertEquals(State.UNBLOCKED, account.getState());
    }

    @Test
    public void getUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        account.setUser(user);
        assertEquals(user, account.getUser());
    }

    @Test
    public void setUser() {
        User user = new User();
        user.setId(2);
        user.setUsername("username-2");
        account.setUser(user);
        assertEquals(user, account.getUser());
    }

    @Test
    public void getCards() {
        Card card = new Card();
        card.setId(5168742300003333L);
        account.setCards(Arrays.asList(card));
        assertEquals(Arrays.asList(card), account.getCards());
    }

    @Test
    public void setCards() {
        Card card = new Card();
        card.setId(5168742300005555L);
        account.setCards(Arrays.asList(card));
        assertEquals(Arrays.asList(card), account.getCards());
    }

    @Test
    public void getPayments() {
        Payment payment = new Payment();
        payment.setId(22);
        account.setPayments(Arrays.asList(payment));
        assertEquals(Arrays.asList(payment), account.getPayments());
    }

    @Test
    public void setPayments() {
        Payment payment = new Payment();
        payment.setId(33);
        account.setPayments(Arrays.asList(payment));
        assertEquals(Arrays.asList(payment), account.getPayments());
    }
}