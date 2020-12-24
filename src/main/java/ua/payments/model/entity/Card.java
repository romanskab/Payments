package ua.payments.model.entity;

import ua.payments.model.entity.enums.State;

import java.util.List;
import java.util.Objects;

public class Card {
    private int id;
    private String number;
    private Account account;
    private State state;
    private List<Payment> payments;

    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
                number.equals(card.number) &&
                account.equals(card.account) &&
                state == card.state &&
                payments.equals(card.payments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, account, state, payments);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", account=" + account +
                ", state=" + state +
                ", payments=" + payments +
                '}';
    }
}
