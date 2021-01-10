package ua.payments.model.dao;

import ua.payments.model.entity.Account;
import ua.payments.model.entity.Payment;
import ua.payments.model.entity.enums.Status;

import java.util.List;

public interface PaymentDao extends GenericDao{
    void create(Payment payment);

    List<Payment> findByAccountId(long accountId);

    List<Payment> findByUserId(int userId);

    int findCountByUserId(int userId);

    List<Payment> findByUserIdAndPagination(int userId, int offset, int limit);

    List<Payment> findByUserAndSortByField(int userId, String field);

    List<Payment> findByUserAndSortByFieldAndPagination(int userId, String field, int offset, int limit);

    List<Payment> findAll();

    Payment findById(int id);

    void changeStatus(int paymentId, Status status);

}
