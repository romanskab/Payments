package ua.payments.model.dao;

import ua.payments.model.entity.Account;
import ua.payments.model.entity.enums.State;
import ua.payments.model.entity.enums.Status;

import java.math.BigDecimal;
import java.util.List;

/**
 * This interface is for AccountDao-classes
 *
 * @author Roman Skab
 * @version 1.0
 */
public interface AccountDao extends GenericDao<Account> {
    void createForClient(int clientId);

    List<Account> findByClientId(int clientId);

    List<Account> findByClientIdAndSortByField(int userId, String field);

    Account findById(long id);

    Account findByIdAndUserId(long id, int userId);

    void increaseBalance(long accountId, BigDecimal sum);

    void decreaseBalance(long accountId, BigDecimal sum, int paymentId);

    Account findByPaymentId(int paymentId);

    void changeState(long accountId, State state);

    List<Account> findByState(State state);
}
