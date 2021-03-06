package ua.payments.model.service;

import ua.payments.model.dao.DaoFactory;
import ua.payments.model.dao.UserDao;
import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;
import ua.payments.model.entity.enums.State;

import java.util.List;

/**
 * This service-class is the way to UserDao
 *
 * @author Roman Skab
 * @version 1.0
 */
public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void create(User user){
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(user);
        }
    }

    public User findByUsernameAndPassword(String username, String password){
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByUsernameAndPassword(username, password);
        }
    }

    public List<User> findByRole(Role role){
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByRole(role);
        }
    }

    public void changeState(State state, int userId){
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.changeState(state, userId);
        }
    }

    public User findById(int id){
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(id);
        }
    }
}
