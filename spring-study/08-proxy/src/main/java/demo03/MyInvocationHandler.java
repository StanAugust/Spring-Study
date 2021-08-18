package demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

//    private Rent rent;
    private Object proxyInterface;

    public void setProxyInterface(Object proxyInterface) {
        this.proxyInterface = proxyInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        visit();
        Object result = method.invoke(proxyInterface, args);
        return result;
    }

    // 代理做的额外操作
    private void visit(){
        System.out.println("中介带着参观房子");
    }

}
