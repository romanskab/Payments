package ua.payments.model.entity;

import ua.payments.model.entity.enums.State;

import java.util.List;
import java.util.Objects;

/**
 * This class represents Card of a client
 *
 * @author Roman Skab
 * @version 1.0
 */
public class Card {
    private long id;
    private Account account;
    private State state;
    private List<Payment> payments;

    public Card() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id &&
                account.equals(card.account) &&
                state == card.state &&
                payments.equals(card.payments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, state, payments);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", account=" + account +
                ", state=" + state +
                ", payments=" + payments +
                '}';
    }
}
