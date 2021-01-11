package ua.payments.model.service;

import ua.payments.model.dao.AccountDao;
import ua.payments.model.dao.DaoFactory;
import ua.payments.model.entity.Account;
import ua.payments.model.entity.enums.State;

import java.math.BigDecimal;
import java.util.List;

/**
 * This service-class is the way to AccountDao
 *
 * @author Roman Skab
 * @version 1.0
 */
public class AccountService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public void createForClient(int clientId) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            accountDao.createForClient(clientId);
        }
    }

    public List<Account> findByClientId(int clientId) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            return accountDao.findByClientId(clientId);
        }
    }

    public List<Account> findByClientIdAndSortByField(int clientId, String field) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            return accountDao.findByClientIdAndSortByField(clientId, field);
        }
    }

    public Account findById(long accountId) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            return accountDao.findById(accountId);
        }
    }

    public void increaseBalance(long accountId, BigDecimal sum) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            accountDao.increaseBalance(accountId, sum);
        }
    }

    public void decreaseBalance(long accountId, BigDecimal sum, int paymentId) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            accountDao.decreaseBalance(accountId, sum, paymentId);
        }
    }

    public Account findByPaymentId(int paymentId) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            return accountDao.findByPaymentId(paymentId);
        }
    }

    public void changeState(long accountId, State state){
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            accountDao.changeState(accountId, state);
        }
    }

    public List<Account> findByState(State state) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            return accountDao.findByState(state);
        }
    }
}
