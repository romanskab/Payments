package ua.payments.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.exception.DaoOperationException;
import ua.payments.model.dao.CardDao;
import ua.payments.model.entity.Account;
import ua.payments.model.entity.Card;
import ua.payments.model.entity.enums.State;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCCardDao implements CardDao {
    private static final Logger logger = LogManager.getLogger(JDBCCardDao.class);

    private static final String INSERT_CARD_SQL = "INSERT INTO card(account_id) VALUE (?)";
    private static final String SELECT_BY_ACCOUNT_ID_SQL = "SELECT * FROM card WHERE account_id = ?";
    private static final String SELECT_BY_USER_ID_SQL = "SELECT * FROM card " +
            "JOIN account ON account.id = card.account_id " +
            "JOIN user ON user.id = account.user_id " +
            "WHERE user_id = ?";
    private static final String SELECT_BY_ID_SQL = "SELECT card.*, account.balance AS balance FROM card " +
            "JOIN account ON card.account_id = account.id\n" +
            "WHERE card.id = ?";

    private Connection connection;

    public JDBCCardDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createForAccount(long accountId) {
        logger.info("createForAccount() started!");
        try (PreparedStatement ps = connection.prepareStatement(INSERT_CARD_SQL)) {
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
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ACCOUNT_ID_SQL)) {
            ps.clearParameters();
            ps.setLong(1, accountId);
            ResultSet rs = ps.executeQuery();
            List<Card> cards = new ArrayList<>();
            while (rs.next()) {
                Card card = new Card();
                card.setId(rs.getLong("id"));
                card.setState(State.valueOf(rs.getString("state")));
                cards.add(card);
            }
            logger.info("found cards: " + cards);
            return cards;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find cards", e);
            logger.error("findByAccountId() failed", exception);
            throw exception;
        }
    }

    @Override
    public List<Card> findByUserId(int userId) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_USER_ID_SQL)) {
            ps.clearParameters();
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Card> cards = new ArrayList<>();
            while (rs.next()) {
                Card card = new Card();
                card.setId(rs.getLong("id"));
                card.setState(State.valueOf(rs.getString("state")));
                cards.add(card);
            }
            logger.info("found cards: " + cards);
            return cards;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find cards", e);
            logger.error("findByUserId() failed", exception);
            throw exception;
        }
    }

    @Override
    public Card findById(long cardId) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.clearParameters();
            ps.setLong(1, cardId);
            ResultSet rs = ps.executeQuery();
            Card card = new Card();
            if (rs.next()) {
                card.setId(rs.getLong("id"));
                card.setState(State.valueOf(rs.getString("state")));
                Account account = new Account();
                account.setId(rs.getLong("account_id"));
                account.setBalance(rs.getBigDecimal("balance"));
                card.setAccount(account);
            }
            logger.info("found card: " + card);
            return card;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find card", e);
            logger.error("findById() failed", exception);
            throw exception;
        }
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
