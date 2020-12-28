package ua.payments.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.model.dao.AccountDao;
import ua.payments.model.dao.CardDao;
import ua.payments.model.dao.DaoFactory;
import ua.payments.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private static final Logger logger = LogManager.getLogger(JDBCDaoFactory.class);

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public AccountDao createAccountDao() {
        return new JDBCAccountDao(getConnection());
    }

    @Override
    public CardDao createCardDao() {
        return new JDBCCardDao(getConnection());
    }

    private Connection getConnection() {
        logger.info("getConnection() started");
        try {
            Connection connection = dataSource.getConnection();
            logger.info("got connection successful: " + connection);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
