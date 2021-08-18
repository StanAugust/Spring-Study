package springAPI;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @ClassName Log
 * @Description 增强类
 * @Author Stan
 * @Date 2021年08月07日
 */
public class Log implements MethodBeforeAdvice, AfterReturningAdvice {
    /**
     * @param method    要执行的目标对象的方法
     * @param args      被调用的方法的参数
     * @param target    目标对象
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("[DEBUG] 执行了 " + target.getClass().getName() + " 的 " + method.getName() + " 方法");
    }

    /**
     * @param returnValue   返回值
     * @param method        被调用的方法
     * @param args          被调用的方法的对象的参数
     * @param target        目标对象
     * @throws Throwable
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("[DEBUG] 执行了 " + target.getClass().getName() + " 的 " + method.getName() + " 方法，返回值：" + returnValue);
    }
}
