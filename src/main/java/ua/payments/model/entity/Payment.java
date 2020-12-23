package ua.payments.model.entity;

import ua.payments.model.entity.enums.Status;

import java.math.BigDecimal;

public class Payment {
    private int id;
    private User user;
    private BigDecimal sum;
    private String comment;
    private Status status;
}
