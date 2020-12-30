package ua.payments.model.service;

import ua.payments.model.dao.DaoFactory;
import ua.payments.model.dao.PaymentDao;
import ua.payments.model.entity.Payment;
import ua.payments.model.entity.enums.Status;

import java.util.List;

public class PaymentService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public void create(Payment payment) {
        try (PaymentDao paymentDao = daoFactory.createPaymentDao()) {
            paymentDao.create(payment);
        }
    }

    public List<Payment> findByAccountId(long accountId){
        try (PaymentDao paymentDao = daoFactory.createPaymentDao()) {
            return paymentDao.findByAccountId(accountId);
        }
    }

    public Payment findById(int id){
        try (PaymentDao paymentDao = daoFactory.createPaymentDao()) {
            return paymentDao.findById(id);
        }
    }

    public void changeStatus(int paymentId, Status status){
        try (PaymentDao paymentDao = daoFactory.createPaymentDao()) {
            paymentDao.changeStatus(paymentId,status);
        }
    }
}
