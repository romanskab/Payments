package ua.payments.model.dao.impl;

import ua.payments.model.dao.UserDao;
import ua.payments.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCUserDao implements UserDao {

    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        final String query = "INSERT INTO user" +
                "(firstName, lastName, role, username, password) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.clearParameters();
            pstmt.setString(1, entity.getFirstName());
            pstmt.setString(2, entity.getLastName());
            pstmt.setString(3, entity.getRole().name());
            pstmt.setString(4, entity.getUsername());
            pstmt.setString(5, entity.getPassword());
            int countAdded = pstmt.executeUpdate();
            System.out.println("added - " + countAdded);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
