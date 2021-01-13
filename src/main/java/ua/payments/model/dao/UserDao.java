package ua.payments.model.dao;

import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;
import ua.payments.model.entity.enums.State;

import java.util.List;

/**
 * This interface is for UserDao-classes
 *
 * @author Roman Skab
 * @version 1.0
 */
public interface UserDao extends GenericDao<User> {

    User findByUsernameAndPassword(String username, String password);

    List<User> findByRole(Role role);

    void changeState(State state, int userId);

}
