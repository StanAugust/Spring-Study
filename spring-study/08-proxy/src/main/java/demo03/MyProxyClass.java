package demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MyProxyClass {
    private Object proxyInterfaces;
    private InvocationHandler handler;

    public void setProxyInterfaces(Object proxyInterfaces) {
        this.proxyInterfaces = proxyInterfaces;
    }
    public void setHandler(InvocationHandler handler) {
        this.handler = handler;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), proxyInterfaces.getClass().getInterfaces(), handler);
    }
}
