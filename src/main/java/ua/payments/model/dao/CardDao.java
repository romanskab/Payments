package ua.payments.model.dao;

import ua.payments.model.entity.Card;

import java.util.List;

public interface CardDao extends GenericDao {
    void createForAccount(long accountId);

    List<Card> findByAccountId(long accountId);

    List<Card> findByUserId(int userId);

    Card findById(long cardId);
}
