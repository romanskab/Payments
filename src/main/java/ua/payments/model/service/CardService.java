package ua.payments.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.model.dao.CardDao;
import ua.payments.model.dao.DaoFactory;
import ua.payments.model.entity.Card;

import java.util.List;

public class CardService {
    private static final Logger logger = LogManager.getLogger(CardService.class);
    DaoFactory daoFactory = DaoFactory.getInstance();

    public void createForAccount(long accountId) {
        logger.info("createForAccount() started!");
        try (CardDao accountDao = daoFactory.createCardDao()) {
            accountDao.createForAccount(accountId);
        }
    }

    public List<Card> findByAccountId(long accountId){
        logger.info("findByAccountId() started!");
        try (CardDao cardDao = daoFactory.createCardDao()) {
            return cardDao.findByAccountId(accountId);
        }
    }

    public List<Card> findByUserId(int userId){
        logger.info("findByUserId() started!");
        try (CardDao cardDao = daoFactory.createCardDao()) {
            return cardDao.findByUserId(userId);
        }
    }

    public Card findById(long cardId){
        logger.info("findById() started!");
        try (CardDao cardDao = daoFactory.createCardDao()) {
            return cardDao.findById(cardId);
        }
    }
}
