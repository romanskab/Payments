package ua.payments.model.dao;

import ua.payments.model.entity.User;

public interface UserDao extends GenericDao<User> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

}
