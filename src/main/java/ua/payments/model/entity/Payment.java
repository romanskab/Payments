package ua.payments.model.entity;

import ua.payments.model.entity.enums.Status;

public class Payment {
    private int id;
    private User user;
    private Status status;
    private int sum;
    private String comment;
}
