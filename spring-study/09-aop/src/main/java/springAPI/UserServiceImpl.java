package springAPI;

// 真实对象，完成增删改查操作的人
public class UserServiceImpl implements UserService{

    @Override
    public void add() {
        System.out.println("add a user");
    }

    @Override
    public void delete() {
        System.out.println("delete a user");
    }

    @Override
    public void update() {
        System.out.println("update a user");
    }

    @Override
    public void select() {
        System.out.println("select a user");
    }
}
