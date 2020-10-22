package ua.payments.model.dao;

import ua.payments.model.entity.User;

public interface UserDao extends GenericDao<User> {
    public User findByUsername(String username);
}
