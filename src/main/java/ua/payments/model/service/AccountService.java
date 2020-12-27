package ua.payments.model.service;

import ua.payments.model.dao.AccountDao;
import ua.payments.model.dao.DaoFactory;
import ua.payments.model.entity.Account;

import java.util.List;

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

    public Account findById(long accountId){
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            return accountDao.findById(accountId);
        }
    }
}
