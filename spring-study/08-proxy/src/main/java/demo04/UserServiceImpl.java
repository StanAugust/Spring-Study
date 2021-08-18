package demo04;

// 真实对象，完成增删改查操作的人
public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("add user");
    }

    @Override
    public void delete() {
        System.out.println("delete user");
    }

    @Override
    public void update() {
        System.out.println("update user");
    }

    @Override
    public void query() {
        System.out.println("query user");
    }
}
