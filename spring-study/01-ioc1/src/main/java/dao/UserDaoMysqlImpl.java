package dao;

public class UserDaoMysqlImpl implements UserDao{

    @Override
    public void getUser() {
        System.out.println("Mysql实现获取用户");
    }
}
