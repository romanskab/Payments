package ua.payments.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.exception.DaoOperationException;
import ua.payments.model.dao.UserDao;
import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;
import ua.payments.model.entity.enums.State;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private static final Logger logger = LogManager.getLogger(JDBCUserDao.class);

    private static final String INSERT_USER_SQL = "INSERT INTO user(firstname, lastname, role, username, password) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user " +
            "WHERE username = ? AND password = ?;";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM user";

    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_USER_SQL)) {
            pstmt.clearParameters();
            pstmt.setString(1, user.getFirstname());
            pstmt.setString(2, user.getLastname());
            pstmt.setString(3, user.getRole().name());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPassword());
            int countAdded = pstmt.executeUpdate();
            logger.info("Created - " + countAdded + " user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    public User findByUsername(String username) {
        try (PreparedStatement pstmt = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            pstmt.clearParameters();
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            User currentUser = users.get(0);
            return currentUser;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD)) {
            logger.info("UserDaoImpl: username - " + username + ", password - " + password);
            ps.setString(1, username);
            ps.setString(2, password);
            return executeFindByUsernameAndPassword(ps);
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot find user by username and password", e);
            logger.error("findByUsernameAndPassword() failed", exception);
            throw exception;
        }
    }

    private User executeFindByUsernameAndPassword(PreparedStatement ps) {
        try (ResultSet rs = ps.executeQuery()) {
            rs.next();
            return parseRow(rs);
        } catch (SQLException e) {
            DaoOperationException exception = new DaoOperationException("Cannot executeFindByUsernameAndPassword", e);
            logger.error("executeFindByUsernameAndPassword() failed", exception);
            throw exception;
        }
    }

    private User parseRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstname(rs.getString("firstname"));
        user.setLastname(rs.getString("lastname"));
        user.setUsername(rs.getString("username"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setState(State.valueOf(rs.getString("state")));
        return user;
    }

    @Override
    public List<User> findAll() {
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(SELECT_ALL_USERS);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setUsername(resultSet.getString("username"));
//                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
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
