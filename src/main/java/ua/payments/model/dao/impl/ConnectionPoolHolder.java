package ua.payments.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static final Logger logger = LogManager.getLogger(ConnectionPoolHolder.class);

    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        logger.info("getDataSource() started!");
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    logger.info("Instantiating DataSource..");
                    final String name = "java:comp/env/jdbc/payments";
                    try {
                        InitialContext ctx = new InitialContext();
                        dataSource = (DataSource) ctx.lookup(name);
                    } catch (NamingException e) {
                        String ex = "NamingException is thrown for name: " + name;
                        throw new RuntimeException(ex, e);
                    }
                    logger.info("Successfully instantiated");
                }
            }
        }
        return dataSource;
    }

}
