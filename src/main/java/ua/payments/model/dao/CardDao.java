package ua.payments.model.dao;

import ua.payments.model.entity.Card;

import java.util.List;

/**
 * This interface is for CardDao-classes
 *
 * @author Roman Skab
 * @version 1.0
 */
public interface CardDao extends GenericDao {
    void createForAccount(long accountId);

    List<Card> findByAccountId(long accountId);

    List<Card> findByUserId(int userId);

    Card findById(long cardId);
}
