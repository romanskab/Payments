package ua.payments.model.dao;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.payments.model.dao.impl.JDBCUserDao;
import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;
import ua.payments.model.entity.enums.State;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {
    private static UserDao userDao;

    private static DataSource dataSource;

    private static final String QUERY = "CREATE TABLE user(\n" +
            "            id INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "            firstname VARCHAR(64) NOT NULL,\n" +
            "            lastname  VARCHAR(64) NOT NULL,\n" +
            "            username VARCHAR(64) NOT NULL UNIQUE,\n" +
            "            password VARCHAR(64) NOT NULL,\n" +
            "            role VARCHAR(32),\n" +
            "            state VARCHAR(32) NOT NULL DEFAULT 'UNBLOCKED'\n" +
            "            ); " +
            "INSERT INTO user (firstName, lastName, username, password, role)\n" +
            "VALUES ('Adminko', 'Anton', 'admin', '341a62a8fd29dff8fc5eaf45f624fc71', 'ROLE_ADMIN');\n" +
            "INSERT INTO user (firstName, lastName, username, password, role)\n" +
            "VALUES ('Danylenko', 'Dmytro', 'd1', '1ff1b92d5fa8efad81bc80e732c28c3b', 'ROLE_CLIENT');\n" +
            "INSERT INTO user (firstName, lastName, username, password, role)\n" +
            "VALUES ('Petrenko', 'Petro', 'p1', 'd7c65ec4a9c55e2f73d743ec7c2ba355', 'ROLE_CLIENT');";

    private static final String DROP_TABLES = "DROP TABLE user";

    @BeforeClass
    public static void beforeClass() throws SQLException {
        dataSource = JdbcUtilH2DB.createDefaultInMemoryH2DataSource();
        createAndFillDBForTests(dataSource);
        userDao = new JDBCUserDao(dataSource.getConnection());
    }

    private static void createAndFillDBForTests(DataSource dataSource) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            conn.createStatement().execute(QUERY);
        }
    }

    @Test(expected = NullPointerException.class)
    public void create() {
        User user = new User();
        userDao.create(user);
    }

    @Test
    public void findByUsernameAndPassword() {
        final User user = userDao.findByUsernameAndPassword("admin", "341a62a8fd29dff8fc5eaf45f624fc71");
        assertEquals(Role.ROLE_ADMIN, user.getRole());
    }

    @Test
    public void findByRole() {
        final List<User> users = userDao.findByRole(Role.ROLE_ADMIN);
        assertEquals(1, users.size());
    }

    @Test
    public void changeState() {
        final User user = userDao.findByUsernameAndPassword("admin", "341a62a8fd29dff8fc5eaf45f624fc71");
        final int userId = user.getId();
        userDao.changeState(State.BLOCKED, userId);
        final User userChanged = userDao.findByUsernameAndPassword("admin", "341a62a8fd29dff8fc5eaf45f624fc71");
        assertEquals(State.BLOCKED, userChanged.getState());
    }
}