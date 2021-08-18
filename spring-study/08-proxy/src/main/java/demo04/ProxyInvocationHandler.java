package demo04;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {

    private Object proxied;    // 被代理的对象

    public void setProxied(Object proxied) {
        this.proxied = proxied;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), proxied.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log(method.getName());  // 这样只需一行代码，即可将该接口下所有方法进行同样的增强
        Object result = method.invoke(proxied, args);
        return result;
    }

    private void log(String msg){
        System.out.println("[DEBUG] 执行了"+msg+"方法");
    }

}
