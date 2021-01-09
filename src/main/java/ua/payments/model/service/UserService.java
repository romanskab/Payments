package ua.payments.model.service;

import ua.payments.model.dao.DaoFactory;
import ua.payments.model.dao.UserDao;
import ua.payments.model.entity.User;
import ua.payments.model.entity.enums.Role;
import ua.payments.model.entity.enums.State;

import java.util.List;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void create(User user){
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(user);
        }
    }

    public List<User> findAll(){
        UserDao userDao = daoFactory.createUserDao();
        return userDao.findAll();
    }

    public User findByUsername(String username){
        UserDao userDao = daoFactory.createUserDao();
        return userDao.findByUsername(username);
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
