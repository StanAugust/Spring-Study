package demo02;

/**
 * 这时新增一个需求：增加日志功能，怎么实现？
 *      思路1 ：在实现类上增加代码 【麻烦！】
 *      思路2：使用代理来做，能够不改变原来的业务情况下，实现此功能就是最好的了！
 */
// 代理角色，在这里面增加日志的实现
public class UserServiceProxy implements UserService{
    private UserServiceImpl userServiceImpl;

    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    private void log(String msg){
        System.out.println("[DEBUG] 执行了"+msg+"方法");
    }

    @Override
    public void add() {
        log("add");
        userServiceImpl.add();
    }

    @Override
    public void delete() {
        log("delete");
        userServiceImpl.delete();
    }

    @Override
    public void update() {
        log("update");
        userServiceImpl.update();
    }

    @Override
    public void query() {
        log("query");
        userServiceImpl.query();
    }
}
