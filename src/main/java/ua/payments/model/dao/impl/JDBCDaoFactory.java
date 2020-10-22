package ua.payments.model.dao.impl;

import ua.payments.model.dao.DaoFactory;
import ua.payments.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        System.out.println("JDBCDaoFactory: createUserDao()");
        return new JDBCUserDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

//        try {
//            return DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/payments",
//                    "root",
//                    "root"
//            );
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
    }

}
