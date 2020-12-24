package ua.payments.model.entity;

import ua.payments.model.entity.enums.Status;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class Payment {
    private int id;
    private User user;
    private BigDecimal sum;
    private String comment;
    private Status status;
    private Timestamp lastModified;
    private Account account;
    private Card card;

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id &&
                user.equals(payment.user) &&
                sum.equals(payment.sum) &&
                comment.equals(payment.comment) &&
                status == payment.status &&
                lastModified.equals(payment.lastModified) &&
                account.equals(payment.account) &&
                card.equals(payment.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, sum, comment, status, lastModified, account, card);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", user=" + user +
                ", sum=" + sum +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                ", lastModified=" + lastModified +
                ", account=" + account +
                ", card=" + card +
                '}';
    }
}
