package ua.payments.model.dao;

import ua.payments.model.dao.impl.JDBCDaoFactory;

/**
 * This class is used for getting instances of DAO-classes
 *
 * @author Roman Skab
 * @version 1.0
 */
public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract AccountDao createAccountDao();
    public abstract CardDao createCardDao();
    public abstract PaymentDao createPaymentDao();

    public static DaoFactory getInstance(){
        if (daoFactory == null){
            synchronized (DaoFactory.class){
                if (daoFactory == null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }


}
