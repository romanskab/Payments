package ua.payments.model.service;

import ua.payments.model.dao.DaoFactory;
import ua.payments.model.dao.UserDao;
import ua.payments.model.entity.User;

import java.util.List;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void create(User user){
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(user);
        }
    }

    public List<User> findAll(){
        System.out.println("UserService: findAll()");
        UserDao userDao = daoFactory.createUserDao();
        return userDao.findAll();
    }

    public User findByUsername(String username){
        System.out.println("UserService: findByUsername()");
        UserDao userDao = daoFactory.createUserDao();
        return userDao.findByUsername(username);
    }

    public User findByUsernameAndPassword(String username, String password){
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByUsernameAndPassword(username, password);
        }
    }
}
