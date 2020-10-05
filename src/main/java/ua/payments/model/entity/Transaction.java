package ua.payments.model.entity;

import ua.payments.model.entity.enums.TransactionType;

public class Transaction {
    private int id;
    private Account account;
    private TransactionType type;
    private int sum;
    private String comment;
}
