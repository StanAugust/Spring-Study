package service;

import dao.UserDao;
import dao.UserDaoImpl;
import dao.UserDaoMysqlImpl;
import dao.UserDaoOracleImpl;

public class UserServiceImpl implements UserService{
    private UserDao userDao = new UserDaoImpl();
//    private UserDao userDao = new UserDaoMysqlImpl();
//    private UserDao userDao = new UserDaoOracleImpl();

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
