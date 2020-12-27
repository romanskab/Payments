package ua.payments.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.exception.DaoOperationException;
import ua.payments.model.dao.CardDao;
import ua.payments.model.entity.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCCardDao implements CardDao {
    private static final Logger logger = LogManager.getLogger(JDBCCardDao.class);

    private static final String INSERT_CARD_SQL = "INSERT INTO card(account_id) VALUE (?)";

    private Connection connection;

    public JDBCCardDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createForAccount(long accountId) {
        logger.info("createForAccount() started!");
        try (PreparedStatement ps = connection.prepareStatement(INSERT_CARD_SQL)) {
            logger.info("try started!");
            ps.clearParameters();
            ps.setLong(1, accountId);
            int countAdded = ps.executeUpdate();
            logger.info("Created - " + countAdded + " card");
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot create card", e);
            logger.error("createForAccount() failed", exception);
            throw exception;
        }
    }

    @Override
    public List<Card> findByAccountId(long accountId) {
        return null;
    }

    @Override
    public void create(Object entity) {

    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
