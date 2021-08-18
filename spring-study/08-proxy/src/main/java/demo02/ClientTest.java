package demo02;

public class ClientTest {
    public static void main(String[] args) {
        // 真实业务
        UserServiceImpl userService = new UserServiceImpl();
        // 代理类
        // 客户端是直接访问代理类的
        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserServiceImpl(userService);

        proxy.add();
    }
}
