package ua.payments.model.dao;

import ua.payments.model.entity.Account;
import ua.payments.model.entity.Payment;
import ua.payments.model.entity.enums.Status;

import java.util.List;

public interface PaymentDao extends GenericDao{
    void create(Payment payment);

    List<Payment> findByAccountId(long accountId);

    List<Payment> findByUserId(int userId);

    List<Payment> findByUserAndSortByField(int userId, String field);

    List<Payment> findAll();

    Payment findById(int id);

    void changeStatus(int paymentId, Status status);

}
