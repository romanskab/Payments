package ua.payments.model.entity;

import ua.payments.model.entity.enums.State;

public class Card {
    private int id;
    private String number;
    private Account account;
    private State state;
}
