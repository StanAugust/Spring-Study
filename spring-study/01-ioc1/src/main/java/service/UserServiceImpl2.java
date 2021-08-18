package service;

import dao.UserDao;

public class UserServiceImpl2 implements UserService{

    private UserDao userDao;

    // 利用set实现值的动态注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
