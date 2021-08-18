package demo03;

public class Client {

    public static void main(String[] args) {
        // 真实角色
        HouseHolder holder = new HouseHolder();

        // 代理类，这里还没生成代理实例
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setRent(holder);    // 设置要代理的角色

        // 动态生成对应的代理类
        Rent proxy = (Rent)pih.getProxy();

        proxy.rent();

//        // 下面是自己看着java api写的proxy class和invocation handler分离版
//        MyInvocationHandler handler = new MyInvocationHandler();
//        handler.setProxyInterface(holder);
//
//        MyProxyClass dynamicProxy = new MyProxyClass();
//        dynamicProxy.setProxyInterfaces(holder);
//        dynamicProxy.setHandler(handler);
//
//        Rent proxy =(Rent)dynamicProxy.getProxy();
//
//        proxy.rent();

    }
}
