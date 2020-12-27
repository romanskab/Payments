package ua.payments.model.entity;

import ua.payments.model.entity.enums.State;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Account {
    private long id;
    private BigDecimal balance;
    private State state;
    private User user;
    private List<Card> cards;
    private List<Payment> payments;

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
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
        Account account = (Account) o;
        return id == account.id &&
                balance.equals(account.balance) &&
                state == account.state &&
                user.equals(account.user) &&
                cards.equals(account.cards) &&
                payments.equals(account.payments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, state, user, cards, payments);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", state=" + state +
                ", user=" + user +
                ", cards=" + cards +
                ", payments=" + payments +
                '}';
    }
}
