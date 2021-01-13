package ua.payments.model.entity;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.payments.model.entity.enums.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CardTest {
    private static Card card;

    @BeforeClass
    public static void beforeClass(){
        card = new Card();
    }

    @Test
    public void getId() {
        card.setId(5169000033336666L);
        assertEquals(5169000033336666L, card.getId());
    }

    @Test
    public void setId() {
        card.setId(5169000033336666L);
        assertNotEquals(5169000033339999L, card.getId());
    }

    @Test
    public void getAccount() {
        Account account = new Account();
        account.setId(2600555557777L);
        card.setAccount(account);
        assertEquals(account, card.getAccount());
    }

    @Test
    public void setAccount() {
        Account account = new Account();
        account.setId(26005555555555L);
        card.setAccount(account);
        assertEquals(account, card.getAccount());
    }

    @Test
    public void getState() {
        card.setState(State.BLOCKED);
        assertEquals(State.BLOCKED, card.getState());
    }

    @Test
    public void setState() {
        card.setState(State.UNBLOCKED);
        assertNotEquals(State.BLOCKED, card.getState());
    }

    @Test
    public void getPayments() {
        List<Payment> payments = new ArrayList<>();
        card.setPayments(payments);
        assertEquals(payments, card.getPayments());
    }

    @Test
    public void setPayments() {
        List<Payment> payments = new ArrayList<>();
        card.setPayments(payments);
        assertEquals(payments, card.getPayments());
    }
}