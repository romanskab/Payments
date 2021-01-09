package ua.payments.model.dao;

import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;
import ua.payments.model.entity.enums.State;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    List<User> findByRole(Role role);

    void changeState(State state, int userId);

}
