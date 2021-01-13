package ua.payments.model.dao;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtilH2DB {
    public static String DEFAULT_DATABASE_NAME = "payments-test";
    public static String DEFAULT_USERNAME = "";
    public static String DEFAULT_PASSWORD = "";

    private static final DataSource h2DataSource = createDefaultInMemoryH2DataSource();

    public static Connection getConnectionToH2DB() {
        try {
            return h2DataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DataSource createDefaultInMemoryH2DataSource() {
        String url = formatH2ImMemoryDbUrl(DEFAULT_DATABASE_NAME);
        return createInMemoryH2DataSource(url, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    private static DataSource createInMemoryH2DataSource(String url, String username, String pass) {
        JdbcDataSource h2DataSource = new JdbcDataSource();
        h2DataSource.setUser(username);
        h2DataSource.setPassword(pass);
        h2DataSource.setUrl(url);

        return h2DataSource;
    }

    private static String formatH2ImMemoryDbUrl(String databaseName) {
        return String.format("jdbc:h2:mem:%s;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false", databaseName);
    }
}
