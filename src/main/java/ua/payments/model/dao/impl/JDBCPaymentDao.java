package ua.payments.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.exception.DaoOperationException;
import ua.payments.model.dao.PaymentDao;
import ua.payments.model.entity.Account;
import ua.payments.model.entity.Payment;
import ua.payments.model.entity.enums.State;
import ua.payments.model.entity.enums.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPaymentDao implements PaymentDao {
    private static final Logger logger = LogManager.getLogger(JDBCPaymentDao.class);

    private static final String INSERT_PAYMENT_SQL = "INSERT INTO payment(sum, comment, account_id) " +
            "VALUES (?, ?, ?)";
    private static final String SELECT_BY_ACCOUNT_ID_SQL = "SELECT * FROM payment WHERE account_id = ?";
    private static final String SELECT_BY_USER_ID_SQL = "SELECT * FROM payment " +
            "JOIN account ON account.id = payment.account_id " +
            "JOIN user ON user.id = account.user_id " +
            "WHERE user_id = ?";
    private static final String SELECT_BY_USER_ID_AND_PAGINATION_SQL = "SELECT * FROM payment " +
            "JOIN account ON account.id = payment.account_id " +
            "JOIN user ON user.id = account.user_id " +
            "WHERE user_id = ? " +
            "LIMIT ?, ?";
    private static final String SELECT_BY_USER_ID_AND_SORT_BY_FIELD_SQL = "SELECT * FROM payment " +
            "JOIN account ON account.id = payment.account_id " +
            "JOIN user ON user.id = account.user_id " +
            "WHERE user_id = ? " +
            "ORDER BY ";
    private static final String SELECT_ALL_SQL = "SELECT * FROM payment";
    private static final String SELECT_BY_ID_SQL = "SELECT payment.*, " +
            "account.id, account.balance, account.state FROM payment " +
            "JOIN account ON account.id = payment.account_id " +
            "WHERE payment.id = ?";
    private static final String UPDATE_STATUS_SQL = "UPDATE payment SET status = ? WHERE id = ?";

    private static final String COUNT_BY_USER_ID_SQL = "SELECT COUNT(1) count FROM payment " +
            "JOIN account ON account.id = payment.account_id " +
            "JOIN user ON user.id = account.user_id " +
            "WHERE user_id = ?";

    private Connection connection;

    public JDBCPaymentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Object entity) {

    }

    @Override
    public Payment findById(int id) {
        logger.info("findById " + id + " started!");
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.clearParameters();
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Payment payment = new Payment();
            if (rs.next()) {
                payment.setId(rs.getInt("id"));
                payment.setComment(rs.getString("comment"));
                payment.setStatus(Status.valueOf(rs.getString("status")));
                payment.setSum(rs.getBigDecimal("sum"));

                Account account = new Account();
                account.setId(rs.getLong("account.id"));
                account.setBalance(rs.getBigDecimal("account.balance"));
                account.setState(State.valueOf(rs.getString("state")));
                payment.setAccount(account);
            }
            logger.info("found payment: " + payment);
            return payment;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find payment", e);
            logger.error("findById() failed", exception);
            throw exception;
        }
    }

    @Override
    public void changeStatus(int paymentId, Status status) {
        logger.info("changeStatus() started!");
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_STATUS_SQL)) {
            ps.clearParameters();
            ps.setString(1, status.name());
            ps.setInt(2, paymentId);
            int countUpdated = ps.executeUpdate();
            logger.info("Updated - " + countUpdated + " payment");
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot update payment", e);
            logger.error("changeStatus() failed", exception);
            throw exception;
        }
    }

    @Override
    public List<Payment> findAll() {
        logger.info("findAll() started!");
        try (Statement statement = connection.createStatement()) {
            final ResultSet rs = statement.executeQuery(SELECT_ALL_SQL);
            List<Payment> payments = new ArrayList<>();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setStatus(Status.valueOf(rs.getString("status")));
                payment.setSum(rs.getBigDecimal("sum"));
                payment.setComment(rs.getString("comment"));
                payments.add(payment);
            }
            logger.info("found payments: " + payments);
            return payments;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find payments", e);
            logger.error("findAll() failed", exception);
            throw exception;
        }
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

    @Override
    public void create(Payment payment) {
        logger.info("create() started!");
        try (PreparedStatement ps = connection.prepareStatement(INSERT_PAYMENT_SQL)) {
            ps.clearParameters();
            ps.setBigDecimal(1, payment.getSum());
            ps.setString(2, payment.getComment());
            ps.setLong(3, payment.getAccount().getId());
            int countCreated = ps.executeUpdate();
            logger.info("Created - " + countCreated + " payment");
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot create payment", e);
            logger.error("create() failed", exception);
            throw exception;
        }
    }

    @Override
    public List<Payment> findByAccountId(long accountId) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ACCOUNT_ID_SQL)) {
            ps.clearParameters();
            ps.setLong(1, accountId);
            ResultSet rs = ps.executeQuery();
            List<Payment> payments = new ArrayList<>();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setStatus(Status.valueOf(rs.getString("status")));
                payment.setSum(rs.getBigDecimal("sum"));
                payment.setComment(rs.getString("comment"));
                payment.setLastModified(rs.getTimestamp("lastModified"));
                payments.add(payment);
            }
            logger.info("found payments: " + payments);
            return payments;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find payments", e);
            logger.error("findByAccountId() failed", exception);
            throw exception;
        }
    }

    @Override
    public List<Payment> findByUserId(int userId) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_USER_ID_SQL)) {
            ps.clearParameters();
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Payment> payments = new ArrayList<>();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setStatus(Status.valueOf(rs.getString("status")));
                payment.setSum(rs.getBigDecimal("sum"));
                payment.setComment(rs.getString("comment"));
                payment.setLastModified(rs.getTimestamp("lastModified"));
                Account account = new Account();
                account.setId(rs.getLong("account_id"));
                payment.setAccount(account);
                payments.add(payment);
            }
            logger.info("found payments: " + payments);
            return payments;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find payments", e);
            logger.error("findByUserId() failed", exception);
            throw exception;
        }
    }

    @Override
    public int findCountByUserId(int userId) {
        try (PreparedStatement ps = connection.prepareStatement(COUNT_BY_USER_ID_SQL)) {
            ps.clearParameters();
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            logger.info("count: " + count);
            return count;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find count", e);
            logger.error("findCountByUserId() failed", exception);
            throw exception;
        }
    }

    @Override
    public List<Payment> findByUserIdAndPagination(int userId, int offset, int limit) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_USER_ID_AND_PAGINATION_SQL)) {
            ps.clearParameters();
            ps.setInt(1, userId);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ResultSet rs = ps.executeQuery();
            List<Payment> payments = new ArrayList<>();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setStatus(Status.valueOf(rs.getString("status")));
                payment.setSum(rs.getBigDecimal("sum"));
                payment.setComment(rs.getString("comment"));
                payment.setLastModified(rs.getTimestamp("lastModified"));
                Account account = new Account();
                account.setId(rs.getLong("account_id"));
                payment.setAccount(account);
                payments.add(payment);
            }
            logger.info("found payments: " + payments);
            return payments;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find payments", e);
            logger.error("findByUserId() failed", exception);
            throw exception;
        }
    }

    @Override
    public List<Payment> findByUserAndSortByField(int userId, String field) {
        logger.info("userId - " + userId);
        logger.info("field - " + field);
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_USER_ID_AND_SORT_BY_FIELD_SQL + field)) {
            ps.clearParameters();
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Payment> payments = new ArrayList<>();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setStatus(Status.valueOf(rs.getString("status")));
                payment.setSum(rs.getBigDecimal("sum"));
                payment.setComment(rs.getString("comment"));
                payment.setLastModified(rs.getTimestamp("lastModified"));
                Account account = new Account();
                account.setId(rs.getLong("account_id"));
                payment.setAccount(account);
                payments.add(payment);
            }
            logger.info("found payments: " + payments);
            return payments;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find payments", e);
            logger.error("findByUserId() failed", exception);
            throw exception;
        }
    }

    @Override
    public List<Payment> findByUserAndSortByFieldAndPagination(int userId, String field, int offset, int limit) {
        logger.info("userId - " + userId);
        logger.info("field - " + field);
        logger.info("offset - " + offset);
        logger.info("limit - " + limit);
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_USER_ID_AND_SORT_BY_FIELD_SQL + field +
                " LIMIT " + offset + ", " + limit)) {
            ps.clearParameters();
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Payment> payments = new ArrayList<>();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setStatus(Status.valueOf(rs.getString("status")));
                payment.setSum(rs.getBigDecimal("sum"));
                payment.setComment(rs.getString("comment"));
                payment.setLastModified(rs.getTimestamp("lastModified"));
                Account account = new Account();
                account.setId(rs.getLong("account_id"));
                payment.setAccount(account);
                payments.add(payment);
            }
            logger.info("found payments: " + payments);
            return payments;
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find payments", e);
            logger.error("findByUserId() failed", exception);
            throw exception;
        }
    }

}
