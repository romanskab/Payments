package ua.payments.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.model.util.Util;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static final Logger logger = LogManager.getLogger(ConnectionPoolHolder.class);

    private static volatile DataSource dataSource;

    public static DataSource getDataSource(){
        logger.info("getDataSource() started!");
        if (dataSource == null){
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(new Util().getPropertyValue("url"));
            ds.setUsername(new Util().getPropertyValue("username"));
            ds.setPassword(new Util().getPropertyValue("password"));
            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);
            dataSource = ds;
        }
        return dataSource;
    }

}
