package ua.payments.model.service;

import ua.payments.model.dao.DaoFactory;
import ua.payments.model.dao.UserDao;
import ua.payments.model.entity.User;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void create(User user){
        System.out.println("UserService: create()");
        UserDao userDao = daoFactory.createUserDao();
        userDao.create(user);
    }
}
