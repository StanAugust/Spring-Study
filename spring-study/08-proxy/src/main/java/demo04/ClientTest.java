package demo04;

public class ClientTest {
    public static void main(String[] args) {
        // 真实业务
        UserServiceImpl userService = new UserServiceImpl();

        // 代理类，这里还没生成代理实例
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setProxied(userService);    // 设置要代理的角色

        // 动态生成对应的代理类
        UserService proxy = (UserService) pih.getProxy();

        proxy.delete();
    }
}
