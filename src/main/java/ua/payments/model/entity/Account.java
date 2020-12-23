package ua.payments.model.entity;

import ua.payments.model.entity.enums.State;

import java.math.BigDecimal;

public class Account {
    private int id;
    private User user;
    private String number;
    private String card;
    private BigDecimal balance;
    private BigDecimal creditLimit;
    private State state;
}
