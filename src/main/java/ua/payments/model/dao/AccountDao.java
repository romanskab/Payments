package ua.payments.model.dao;

import ua.payments.model.entity.Account;

import java.util.List;

public interface AccountDao extends GenericDao<Account> {
    void createForClient(int clientId);

    List<Account> findByClientId(int clientId);

    Account findById(long id);

    Account findByIdAndUserId(long id, int userId);
}
