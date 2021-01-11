package ua.payments.model.entity;

import ua.payments.model.entity.enums.Role;
import ua.payments.model.entity.enums.State;

import java.util.List;
import java.util.Objects;

/**
 * This class represents User
 *
 * @author Roman Skab
 * @version 1.0
 */
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Role role;
    private State state;
    private List<Account> accounts;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                firstname.equals(user.firstname) &&
                lastname.equals(user.lastname) &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                role == user.role &&
                state == user.state &&
                accounts.equals(user.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, username, password, role, state, accounts);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", state=" + state +
                ", accounts=" + accounts +
                '}';
    }
}
