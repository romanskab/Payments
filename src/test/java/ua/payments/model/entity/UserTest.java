package ua.payments.model.entity;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.payments.model.entity.enums.Role;
import ua.payments.model.entity.enums.State;

import java.util.Arrays;

import static org.junit.Assert.*;

public class UserTest {
    private static User user;

    @BeforeClass
    public static void beforeClass(){
        user = new User();
    }

    @Test
    public void getId() {
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    public void setId() {
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    public void getFirstname() {
        user.setFirstname("Firstname");
        assertEquals("Firstname", user.getFirstname());
    }

    @Test
    public void setFirstname() {
        user.setFirstname("Firstname-2");
        assertEquals("Firstname-2", user.getFirstname());
    }

    @Test
    public void getLastname() {
        user.setLastname("Lastname");
        assertEquals("Lastname", user.getLastname());
    }

    @Test
    public void setLastname() {
        user.setLastname("Lastname-2");
        assertEquals("Lastname-2", user.getLastname());
    }

    @Test
    public void getUsername() {
        user.setUsername("Username");
        assertEquals("Username", user.getUsername());
    }

    @Test
    public void setUsername() {
        user.setUsername("Username-2");
        assertEquals("Username-2", user.getUsername());
    }

    @Test
    public void getPassword() {
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    @Test
    public void setPassword() {
        user.setPassword("password-2");
        assertEquals("password-2", user.getPassword());
    }

    @Test
    public void getRole() {
        user.setRole(Role.ROLE_CLIENT);
        assertEquals(Role.ROLE_CLIENT, user.getRole());
    }

    @Test
    public void setRole() {
        user.setRole(Role.ROLE_ADMIN);
        assertEquals(Role.ROLE_ADMIN, user.getRole());
    }

    @Test
    public void getState() {
        user.setState(State.BLOCKED);
        assertEquals(State.BLOCKED, user.getState());
    }

    @Test
    public void setState() {
        user.setState(State.UNBLOCKED);
        assertEquals(State.UNBLOCKED, user.getState());
    }

    @Test
    public void getAccounts() {
        user.setAccounts(Arrays.asList(new Account()));
        assertNotNull(user.getAccounts());
    }

    @Test
    public void setAccounts() {
        user.setAccounts(null);
        assertNull(user.getAccounts());
    }
}