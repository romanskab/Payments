package ua.payments.model.service;

import ua.payments.model.dao.DaoFactory;
import ua.payments.model.dao.UserDao;
import ua.payments.model.entity.User;

import java.util.List;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void create(User user){
        System.out.println("UserService: create()");
        UserDao userDao = daoFactory.createUserDao();
        userDao.create(user);
    }

    public List<User> findAll(){
        System.out.println("UserService: findAll()");
        UserDao userDao = daoFactory.createUserDao();
        return userDao.findAll();
    }
}
