package ua.payments.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.model.dao.CardDao;
import ua.payments.model.dao.DaoFactory;

public class CardService {
    private static final Logger logger = LogManager.getLogger(CardService.class);
    DaoFactory daoFactory = DaoFactory.getInstance();

    public void createForAccount(long accountId) {
        logger.info("createForAccount() started!");
        try (CardDao accountDao = daoFactory.createCardDao()) {
            accountDao.createForAccount(accountId);
        }
    }
}
