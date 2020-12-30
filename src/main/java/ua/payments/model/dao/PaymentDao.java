package ua.payments.model.dao;

import ua.payments.model.entity.Account;
import ua.payments.model.entity.Payment;
import ua.payments.model.entity.enums.Status;

import java.util.List;

public interface PaymentDao extends GenericDao{
    void create(Payment payment);

    List<Payment> findByAccountId(long accountId);

    Payment findById(int id);

    void changeStatus(int paymentId, Status status);

}
