package ua.payments.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.exception.DaoOperationException;
import ua.payments.model.dao.AccountDao;
import ua.payments.model.entity.Account;
import ua.payments.model.entity.enums.State;
import ua.payments.model.entity.enums.Status;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCAccountDao implements AccountDao {
    private static final Logger logger = LogManager.getLogger(JDBCAccountDao.class);

    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO account(user_id) " +
            "VALUE (?)";
    private static final String SELECT_BY_USER_ID_SQL = "SELECT * FROM account WHERE user_id = ?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM account WHERE id = ?";
    private static final String SELECT_BY_ID_AND_USER_ID_SQL = "SELECT * FROM account WHERE id = ? AND user_id = ?";
    private static final String INCREASE_BALANCE_SQL = "UPDATE account SET balance = balance + ? WHERE id = ?";
    private static final String DECREASE_BALANCE_SQL = "UPDATE account SET balance = balance - ? WHERE id = ?";
    private static final String SELECT_BY_PAYMENT_ID_SQL = "SELECT * FROM account " +
            "JOIN payment ON account.id = payment.account_id " +
            "WHERE payment.id = ?";
    private static final String UPDATE_STATE_SQL = "UPDATE account SET state = ? WHERE id = ?";
    private static final String SELECT_BY_STATE_SQL = "SELECT * FROM account WHERE state = ?";

    private Connection connection;

    public JDBCAccountDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Account entity) {

    }

    @Override
    public Account findById(int id) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public void update(Account entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public void createForClient(int clientId) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_ACCOUNT_SQL)) {
            ps.clearParameters();
            ps.setInt(1, clientId);
            int countAdded = ps.executeUpdate();
            logger.info("Created - " + countAdded + " account");
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot create account", e);
            logger.error("createForClient() failed", exception);
            throw exception;
        }
    }

    @Override
    public List<Account> findByClientId(int clientId) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_USER_ID_SQL)) {
            ps.clearParameters();
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getLong("id"));
                account.setBalance(rs.getBigDecimal("balance"));
                account.setState(State.valueOf(rs.getString("state")));
                accounts.add(account);
            }
            logger.info("found accounts: " + accounts);
            return accounts;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find accounts", e);
            logger.error("findByClientId() failed", exception);
            throw exception;
        }
    }

    @Override
    public Account findById(long id) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.clearParameters();
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Account account = new Account();
            if (rs.next()) {
                account.setId(rs.getLong("id"));
                account.setBalance(rs.getBigDecimal("balance"));
                account.setState(State.valueOf(rs.getString("state")));
            }
            logger.info("found account: " + account);
            return account;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find accounts", e);
            logger.error("findByClientId() failed", exception);
            throw exception;
        }
    }

    @Override
    public Account findByIdAndUserId(long id, int userId) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID_AND_USER_ID_SQL)) {
            ps.clearParameters();
            ps.setLong(1, id);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            Account account = new Account();
            if (rs.next()) {
                account.setId(rs.getLong("id"));
                account.setBalance(rs.getBigDecimal("balance"));
                account.setState(State.valueOf(rs.getString("state")));
            }
            logger.info("found account: " + account);
            return account;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find account", e);
            logger.error("findByClientId() failed", exception);
            throw exception;
        }
    }

    @Override
    public void increaseBalance(long accountId, BigDecimal sum) {
        logger.info("increaseBalance() started!");
        try (PreparedStatement ps = connection.prepareStatement(INCREASE_BALANCE_SQL)) {
            ps.clearParameters();
            ps.setBigDecimal(1, sum);
            ps.setLong(2, accountId);
            int countUpdated = ps.executeUpdate();
            logger.info("Updated - " + countUpdated + " account");
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot update account", e);
            logger.error("increaseBalance() failed", exception);
            throw exception;
        }
    }

    @Override
    public void decreaseBalance(long accountId, BigDecimal sum, int paymentId) {
        logger.info("decreaseBalance() started!");
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Cannot set AutoCommit false!!!");
            e.printStackTrace();
        }
        try (PreparedStatement ps = connection.prepareStatement(DECREASE_BALANCE_SQL)) {
            ps.clearParameters();
            ps.setBigDecimal(1, sum);
            ps.setLong(2, accountId);
            int countUpdated = ps.executeUpdate();
            logger.info("Updated - " + countUpdated + " account");
            final JDBCPaymentDao paymentDao = new JDBCPaymentDao(connection);
            paymentDao.changeStatus(paymentId, Status.PAID);
            connection.commit();
            logger.info("Done!");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("Cannot do rollback!!!");
                ex.printStackTrace();
            }
            DaoOperationException exception = new DaoOperationException("Cannot decrease balance", e);
            logger.error("decreaseBalance() failed", exception);
            throw exception;
        }
    }

    @Override
    public Account findByPaymentId(int paymentId) {
        logger.info("findByPaymentId() started! - " + paymentId);
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_PAYMENT_ID_SQL)) {
            ps.clearParameters();
            ps.setInt(1, paymentId);
            ResultSet rs = ps.executeQuery();
            Account account = new Account();
            if (rs.next()) {
                account.setId(rs.getLong("id"));
                account.setBalance(rs.getBigDecimal("balance"));
                account.setState(State.valueOf(rs.getString("state")));
            }
            logger.info("found account: " + account);
            return account;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find account", e);
            logger.error("findByPaymentId() failed", exception);
            throw exception;
        }
    }

    @Override
    public void changeState(long accountId, State state) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_STATE_SQL)) {
            ps.clearParameters();
            ps.setString(1, state.name());
            ps.setLong(2, accountId);
            int countUpdated = ps.executeUpdate();
            logger.info("Updated - " + countUpdated + " account");
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot change state", e);
            logger.error("changeState() failed", exception);
            throw exception;
        }
    }

    @Override
    public List<Account> findByState(State state) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_STATE_SQL)) {
            ps.clearParameters();
            ps.setString(1, state.name());
            ResultSet rs = ps.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getLong("id"));
                account.setBalance(rs.getBigDecimal("balance"));
                account.setState(State.valueOf(rs.getString("state")));
                accounts.add(account);
            }
            logger.info("found accounts: " + accounts);
            return accounts;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find accounts", e);
            logger.error("findByState() failed", exception);
            throw exception;
        }
    }
}
