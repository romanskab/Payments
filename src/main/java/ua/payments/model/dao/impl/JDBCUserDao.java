package ua.payments.model.dao.impl;

import ua.payments.model.dao.UserDao;
import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {

    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        final String query = "INSERT INTO user" +
                "(firstName, lastName, role, username, password) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.clearParameters();
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getRole().name());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPassword());
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

    public User findByUsername(String username) {
        System.out.println("JDBCUserDao: findByUsername");
        final String query = "SELECT * FROM user WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.clearParameters();
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            System.out.println("users:\n" + users);

            User currentUser = users.get(0);
            System.out.println("currentUser: " + currentUser);
            return currentUser;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        final String query = "SELECT * FROM user";

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setUsername(resultSet.getString("username"));
//                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }

            System.out.println("users:\n" + users);
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
