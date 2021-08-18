package demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {

    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        visit();
        Object result = method.invoke(rent, args);
        return result;
    }

    private void visit(){
        System.out.println("中介带着参观房子");
    }

    private void collectFees(){
        System.out.println("收取中介费");
    }

    private void signContract(){
        System.out.println("签订合同");
    }
}
